
package day_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AOC5_2 {
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

    System.out.println("Sum of middle entries in invalid lists: " + sum);
  }

  private static int identifyAndCalculate(HashMap<String, ArrayList<String>> map, List<String> list) {

    LinkedHashMap<String, Integer> mapCounters = new LinkedHashMap<>();
    for (int i = 0; i < list.size(); i++) {
      mapCounters.put(list.get(i), 0);
      List<String> leftList = list.subList(0, i);
      List<String> rightList = list.subList(i + 1, list.size());
      List<String> subList = Stream.concat(leftList.stream(), rightList.stream())
              .toList();

      ArrayList<String> arrayList = map.get(list.get(i));
      if (arrayList == null) {
        mapCounters.put(list.get(i), Integer.MAX_VALUE);
        continue;
      }
      for (String s : subList) {
        int index = arrayList.indexOf(s);
        if (index == -1) {
          mapCounters.put(list.get(i), mapCounters.get(list.get(i)) + 1);
        }
      }
    }

    LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

    mapCounters.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

    List<String> strings = sortedMap.keySet().stream().toList();
    if (!IntStream.range(0, strings.size())
            .allMatch(i -> strings.get(i).equals(list.get(i)))) {
      return Integer.parseInt(strings.get(strings.size()/2));
    }
    return 0;
  }

}
