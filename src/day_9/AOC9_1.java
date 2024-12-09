
package day_9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.util.Collections.swap;

public class AOC9_1 {

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

      int j = extractLastNumber(numbers);
      int lastNumber = j;
      for (int i = 0; i < numbers.size(); i++) {
        if ( i >= lastNumber) {
          break;
        }
        if (Objects.equals(numbers.get(i), ".")) {
          swap(numbers, i, lastNumber);
          lastNumber = extractLastNumber(numbers);
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

  private static int extractLastNumber(ArrayList<String> numbers) {
    int j = numbers.size() -1;
    for (; j >= 0; j--) {
      if (!numbers.get(j).equals(".")) {
        break;
      }
    }
    return j;
  }
}


//0099811188827773336446555566..............