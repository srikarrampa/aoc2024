
package day_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AOC6_2 {
  private static int count = 0;

  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-6.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      ArrayList<char[]> matrix  = new ArrayList<>();
      int startX = 0;
      int startY = 0;
      int rowIndex = 0;
      while ((line = br.readLine()) != null) {
        char[] row = line.toCharArray();
        int i = line.indexOf('^');
        if (i != -1) {
          startX = rowIndex;
          startY = i;

        }
        matrix.add(row);
        rowIndex++;
      }


      for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix.get(0).length; j++) {
          char[][] input = new char[matrix.size()][matrix.get(0).length];

          for (int row = 0; row < matrix.size(); row++) {
            input[row] = Arrays.copyOf(matrix.get(row), matrix.get(row).length);
          }

          input[startX][startY] = 'X';
          if (input[i][j] == '.') {
            input[i][j] = 'O';
            traverse(input, startX, startY, 0, new boolean[input.length][input[0].length][4]);
          }
        }
      }


    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + count);
  }

  private static void traverse(char[][] input, int startX, int startY, int dir, boolean[][][] visited) {
    if (input[startX][startY] == '.')
      input[startX][startY] = 'X';
    if (startX <= 0 || startY <= 0 || startX >= input.length -1 || startY >= input[0].length - 1) {
      return;
    } else if (visited[startX][startY][dir]) {
      count++;
      return;
    }

    visited[startX][startY][dir] = true;
    if (dir == 0) {
      startX -= 1;
      if (input[startX][startY] == '#' || input[startX][startY] == 'O') {
        dir = (dir + 1) % 4;
        traverse(input, startX + 1, startY, dir, visited);
      } else {
        traverse(input, startX, startY, dir, visited);
      }
    } else if (dir == 1) {
      startY += 1;
      if (input[startX][startY] == '#' || input[startX][startY] == 'O') {
        dir = (dir + 1) % 4;
        traverse(input, startX, startY - 1, dir, visited);
      } else {
        traverse(input, startX, startY, dir, visited);
      }
    } else if (dir == 2) {
      startX +=1;
      if (input[startX][startY] == '#' || input[startX][startY] == 'O') {
        dir = (dir + 1) % 4;
        traverse(input, startX - 1, startY, dir, visited);
      } else {
        traverse(input, startX, startY, dir, visited);
      }
    } else {
      startY -=1;
      if (input[startX][startY] == '#' || input[startX][startY] == 'O') {
        dir = (dir + 1) % 4;
        traverse(input, startX, startY + 1, dir, visited);
      } else {
        traverse(input, startX, startY, dir, visited);
      }
    }
  }
}
