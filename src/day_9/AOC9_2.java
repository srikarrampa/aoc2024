
package day_9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Collections.swap;

public class AOC9_2 {

  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-9.txt";
    long sum = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      ArrayList<String> numbers = new ArrayList<>();
      int id = 0;
      while ((line = br.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          int repeatValue = Integer.parseInt(String.valueOf(line.charAt(i)));

          if (i % 2 == 0) {
            for (int j = 0; j < repeatValue; j++) {
              numbers.add(id + "");
            }
            id++;
          } else {
            for (int j = 0; j < repeatValue; j++) {
              numbers.add(".");
            }
          }
        }
      }

      int lastOccurance = extractLastNumberLastOccurrence(numbers, numbers.size() - 1);
      int firstOccurence = extractLastNumberFirstOccurrence(numbers, lastOccurance);

      for (int i = 0; i < numbers.size(); i++) {
        if (lastOccurance <= 0) {
          break;
        }
        // reset
        if (i >= firstOccurence) {
          lastOccurance = extractLastNumberLastOccurrence(numbers, firstOccurence - 1);
          firstOccurence = extractLastNumberFirstOccurrence(numbers, lastOccurance);
          i = 0;
        }

        boolean possible = true;
        if (Objects.equals(numbers.get(i), ".")) {
          for (int j = i; j - i <= lastOccurance - firstOccurence; j++) {
            if (!Objects.equals(numbers.get(j), ".")) {
              possible = false;
              break;
            }
          }
          if (possible) {
            int temp = lastOccurance;
            for (int j = i; j - i <= lastOccurance - firstOccurence; j++) {
              swap(numbers, j, temp--);
            }
            lastOccurance = extractLastNumberLastOccurrence(numbers, firstOccurence - 1);
            firstOccurence = extractLastNumberFirstOccurrence(numbers, lastOccurance);
            i = 0;
          }
        }
      }

      for (int i = 0; i < numbers.size(); i++) {
        if (numbers.get(i).equals(".")) {
          continue;
        }
        sum += i * Long.parseLong(numbers.get(i));
      }

    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Sum: " + sum);
  }

  private static int extractLastNumberLastOccurrence(ArrayList<String> numbers, int firstOccurence) {
    int j = firstOccurence;
    for (; j >= 0; j--) {
      if (!numbers.get(j).equals(".")) {
        break;
      }
    }
    return j;
  }

  private static int extractLastNumberFirstOccurrence(ArrayList<String> numbers, int lastOccurence) {
    int j = lastOccurence - 1;
    for (; j >= 0; j--) {
      if (!numbers.get(j).equals(numbers.get(lastOccurence))) {
        break;
      }
    }
    return j + 1;
  }
}

// 0099.111777244.333....5555.6666.....8888..