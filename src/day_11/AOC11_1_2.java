
package day_11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC11_1_2 {
  public static long sum = 0;
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-11.txt";

    ArrayList<String> puzzleInput = new ArrayList<>();


    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] input = line.split(" ");
        Collections.addAll(puzzleInput, input);
      }

      HashMap<String, Long> count = new HashMap<>();
      for (String st : puzzleInput) {
        count.put(st, count.getOrDefault(st, 0L) + 1);
      }
      for (int i = 0; i < 75; i++) {
        HashMap<String, Long> nextCount = new HashMap<>();
        for (Map.Entry<String, Long> e : count.entrySet()) {
          puzzleInput = calculateSplit(e.getKey());
          for (String st : puzzleInput) {
            nextCount.put(st, nextCount.getOrDefault(st, 0L) + e.getValue());
          }
        }
        count = nextCount;
      }
      sum = count.values().stream().mapToLong(i -> i).sum();

    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + sum);
  }

  private static ArrayList<String> calculateSplit(String input) {
    ArrayList<String> res = new ArrayList<>();
      long longValue = Long.parseLong(input);
      if (longValue == 0) {
        res.add("1");
      } else if (input.length() % 2 == 0) {

        String firstPart = input.substring(0, input.length() / 2);
        res.add(String.valueOf(Long.parseLong(firstPart)));

        String secondPart = input.substring(input.length()/2);
        res.add(String.valueOf(Long.parseLong(secondPart)));
      } else {
        res.add(String.valueOf(longValue*2024));
      }
    return res;
  }
}

