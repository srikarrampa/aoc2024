
package day_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AOC3_1 {

  public static final String MUL_REGEX = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
  public static final String DO_REGEX = "do\\(\\)";
  public static final String DONT_REGEX = "don't\\(\\)";

  public static void main(String[] args) {

    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-3.txt";
    long sum = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {

        Pattern pattern = Pattern.compile(MUL_REGEX);

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          String matchedValue = matcher.group();

          int firstNumberEndIndex = findValueEndIndex(matchedValue, 4);
          int firstNumber = Integer.parseInt(matchedValue.substring(4, firstNumberEndIndex));
          int secondNumberEndIndex = findValueEndIndex(matchedValue, firstNumberEndIndex + 1);
          int secondNumber = Integer.parseInt(matchedValue.substring(firstNumberEndIndex + 1, secondNumberEndIndex));

          sum += ((long) firstNumber * secondNumber);
        }

      }
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Sum: " + sum);

  }

  private static int findValueEndIndex(String matchedValue, int index) {
    char[] matchedValueCharArray = matchedValue.toCharArray();
    for (int i = index; i < matchedValueCharArray.length; i++) {
      if (Character.isDigit(matchedValueCharArray[i])) {
        continue;
      }
      return i;
    }
    return -1;
  }
}
