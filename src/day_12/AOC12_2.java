/*
 * Copyright (c) 2023 Splunk, Inc. All rights reserved.
 */

package day_12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AOC12_2 {
  public static int[][] DIRS = {{1,0}, {0,-1}, {-1,0}, {0, 1}};
  public static char[][] input;

  public static boolean[][] visited;

  public static long sum = 0;
  public static long angles = 0;
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-12.txt";

    ArrayList<String> puzzleInput = new ArrayList<>();
    HashMap<Character, ArrayList<Long>> areaMap = new HashMap<>();
    HashMap<Character, ArrayList<Long>> sidesMap = new HashMap<>();


    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        puzzleInput.add(line);

      }
      input = new char[puzzleInput.size()][puzzleInput.get(0).length()];

      for (int i = 0; i < puzzleInput.size(); i++) {
        for (int j = 0; j < puzzleInput.get(i).length(); j++) {
          input[i][j] = puzzleInput.get(i).charAt(j);
        }
      }
      visited = new boolean[puzzleInput.size()][puzzleInput.get(0).length()];

      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          if (!visited[i][j]) {
            long areaResult = calculateArea(i, j, input, input[i][j]);
            areaMap.computeIfAbsent(input[i][j], k -> new ArrayList<>()).add(areaResult);
            sum += areaResult * angles;
            angles = 0;

          }
        }
      }


    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + sum);
  }

  private static long calculateArea(int i, int j, char[][] input, char c) {

    if (i < 0 ||
            j < 0 ||
            i >= input.length ||
            j >= input[0].length || input[i][j] != c || visited[i][j]) {
      return 0;
    }

    visited[i][j] = true;

    if (!same_plant(i-1,j,c) && !same_plant(i,j+1,c)) angles++;
    if (!same_plant(i,j+1,c) && !same_plant(i+1,j,c)) angles++;
    if (!same_plant(i+1,j,c) && !same_plant(i,j-1,c)) angles++;
    if (!same_plant(i,j-1,c) && !same_plant(i-1,j,c)) angles++;
    // inside angles
    if (same_plant(i-1,j,c) && same_plant(i,j+1,c) && !same_plant(i-1,j+1,c)) angles++;
    if (same_plant(i,j+1,c) && same_plant(i+1,j,c) && !same_plant(i+1,j+1,c)) angles++;
    if (same_plant(i+1,j,c) && same_plant(i,j-1,c) && !same_plant(i+1,j-1,c)) angles++;
    if (same_plant(i,j-1,c) && same_plant(i-1,j,c) && !same_plant(i-1,j-1,c)) angles++;

    long right = calculateArea(i, j + 1, input, input[i][j]);
    long left = calculateArea(i, j - 1, input, input[i][j]);
    long down = calculateArea(i + 1, j, input, input[i][j]);
    long up = calculateArea(i - 1, j, input, input[i][j]);
    return 1 + right +
            left +
            down +
            up;
  }

  private static boolean same_plant(int i, int j, char c) {
    return i >= 0 &&
            j >= 0 &&
            i < input.length &&
            j < input[0].length && input[i][j] == c;
  }
}
