
package day_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOC5_1 {
  static int count = 0;

  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-5.txt";
    long sum = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      HashMap<String, ArrayList<String>> map = new HashMap<>();
      ArrayList<List<String>> pages  = new ArrayList<>();
      boolean linebreak = false;
      while ((line = br.readLine()) != null) {
        if (line.isEmpty()) {
          linebreak = true;
        } else if (linebreak) {
          pages.add(Arrays.asList(line.split(",")));
        } else {
          String[] values = line.split("\\|");
          map.computeIfAbsent(values[0], k -> new ArrayList<>()).add(values[1]);
        }
      }

      for (List<String> page : pages) {
        sum += identifyAndCalculate(map, page);
      }

    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Sum of middle entries in the valid lists: " + sum);
  }

  private static int identifyAndCalculate(HashMap<String, ArrayList<String>> map, List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      List<String> subList = list.subList(i + 1, list.size());
      ArrayList<String> arrayList = map.get(list.get(i));
      if (arrayList == null) {
        return 0;
      }
      for (String s : subList) {
        int index = arrayList.indexOf(s);
        if (index == -1) {
          return 0;
        }
      }
    }
    return Integer.parseInt(list.get(list.size()/2));
  }

}
