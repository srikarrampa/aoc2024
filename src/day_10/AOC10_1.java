/*
 * Copyright (c) 2023 Splunk, Inc. All rights reserved.
 */

package day_10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class AOC10_1 {

  public static int[][] DIRS = {{1,0}, {0,-1}, {-1,0}, {0, 1}};
  public static int[][] input;
  public static int count = 0;
  public static HashSet<String> visited = new HashSet<>();
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-10.txt";

    ArrayList<String> puzzleInput = new ArrayList<>();


    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        puzzleInput.add(line);
      }
      input = new int[puzzleInput.size()][puzzleInput.get(0).length()];

      for (int i = 0; i < puzzleInput.size(); i++) {
        for (int j = 0; j < puzzleInput.get(i).length(); j++) {
          input[i][j] = Integer.parseInt(String.valueOf(puzzleInput.get(i).charAt(j)));
        }
      }

      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          if (input[i][j] == 0) {
            calculateCount(i, j, input);
            visited = new HashSet<>();
          }
        }
      }


    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + count);
  }

  private static void calculateCount(int i, int j, int[][] input) {

    if (input[i][j] == 9) {
      if (!visited.contains(i + "," + j)) {
        count++;
        visited.add(i + "," + j);
      }

      return;
    }
    for (int[] dir : DIRS) {

      int newX = i + dir[0];
      int newY = j + dir[1];

      if (newX < 0 ||
              newY < 0 ||
              newX >= input.length ||
              newY >= input[0].length || input[newX][newY] != input[i][j] + 1) {
        continue;
      }
      calculateCount(newX, newY, input);
    }
  }
}
