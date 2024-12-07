
package day_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AOC7_2 {
  private static ArrayList<Long> validNumbers = new ArrayList<>();
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-7.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] values = line.split(":");
        long sum = Long.parseLong(values[0]);

        String[] parts = values[1].split(" ");
        long[] numbers = new long[parts.length - 1];
        for (int i = 1; i < parts.length; i++) {
          numbers[i - 1] = Long.parseLong(parts[i]);
        }
        if (validNumber(1, sum, numbers[0], numbers)) {
          validNumbers.add(sum);
        }
      }


    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Sum: " + validNumbers.stream().mapToLong(Long::longValue).sum());
  }

  private static boolean validNumber(int index, long sum, long currSum, long[] numbers) {
    if (index == numbers.length) {
      return currSum == sum;
    } else if (currSum > sum) {
      return false;
    }
    if (index + 1 <= numbers.length) {
      if (validNumber(index + 1, sum, currSum + numbers[index], numbers)) {
        return true;
      } else if (validNumber(index + 1, sum, currSum * numbers[index], numbers)) {
        return true;
      } else {
        return validNumber(index + 1, sum, Long.parseLong("" + currSum + numbers[index]), numbers);
      }
    }
    return false;
  }
}
