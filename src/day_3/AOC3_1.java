/*
 * Copyright (c) 2023 Splunk, Inc. All rights reserved.
 */

package day_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Pattern doPattern = Pattern.compile(DO_REGEX);
        Pattern dontPattern = Pattern.compile(DONT_REGEX);
        
        Matcher doMatcher = doPattern.matcher(line);
        Matcher dontMatcher = dontPattern.matcher(line);

        List<Integer> doResults = doMatcher.results().map(MatchResult::start).toList();
        List<Integer> dontResults = dontMatcher.results().map(MatchResult::start).toList();

        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          String matchedValue = matcher.group();
          int matchedStartValue = matcher.start();

          int indexDoStart = Collections.binarySearch(doResults, matchedStartValue);
          indexDoStart = -(indexDoStart + 1) - 1;
          int indexDontStart = Collections.binarySearch(dontResults, matchedStartValue);
          indexDontStart = -(indexDontStart + 1) - 1;

          if (indexDontStart == -1) {
            sum += calculateSum(matchedValue);
            continue;
          }

          if (indexDoStart != doResults.size() && indexDontStart != dontResults.size()) {
            if (matchedStartValue - doResults.get(indexDoStart) > matchedStartValue - dontResults.get(indexDontStart))
              continue;
          } else {
            if (dontResults.get(dontResults.size() - 1) > doResults.get(doResults.size() - 1)) {
              continue;
            }
          }
          long tempSum = calculateSum(matchedValue);
          sum += tempSum;
        }

      }
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + sum);
  }

  private static long calculateSum(String matchedValue) {
    int firstNumberEndIndex = findValueEndIndex(matchedValue, 4);
    int firstNumber = Integer.parseInt(matchedValue.substring(4, firstNumberEndIndex));
    int secondNumberEndIndex = findValueEndIndex(matchedValue, firstNumberEndIndex + 1);
    int secondNumber = Integer.parseInt(matchedValue.substring(firstNumberEndIndex + 1, secondNumberEndIndex));

    long tempSum = (long) firstNumber * secondNumber;
    return tempSum;
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
