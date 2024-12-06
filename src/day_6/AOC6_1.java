
package day_6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AOC6_1 {

  private static char[][] input;
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
      input = matrix.toArray(char[][]::new);
      input[startX][startY] = 'X';
      traverse(startX, startY, 0);
      calculateX(input);

    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + count);
  }

  private static void calculateX(char[][] input) {
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[0].length; j++) {
        if (input[i][j] == 'X') {
          count++;
        }
      }
    }
  }

  private static void traverse(int startX, int startY, int dir) {
    if (input[startX][startY] == '.')
      input[startX][startY] = 'X';
    if (startX <= 0 || startY <= 0 || startX >= input.length -1 || startY >= input[0].length - 1 ) {

      return;
    }

    if (dir == 0) {
      startX -= 1;
      if (input[startX][startY] == '#') {
        dir = (dir + 1) % 4;
        traverse(startX + 1, startY, dir);
      } else {
        traverse(startX, startY, dir);
      }
    } else if (dir == 1) {
      startY += 1;
      if (input[startX][startY] == '#') {
        dir = (dir + 1) % 4;
        traverse(startX, startY - 1, dir);
      } else {
        traverse(startX, startY, dir);
      }
    } else if (dir == 2) {
      startX +=1;
      if (input[startX][startY] == '#') {
        dir = (dir + 1) % 4;
        traverse(startX - 1, startY, dir);
      } else {
        traverse(startX, startY, dir);
      }
    } else {
      startY -=1;
      if (input[startX][startY] == '#') {
        dir = (dir + 1) % 4;
        traverse(startX, startY + 1, dir);
      } else {
        traverse(startX, startY, dir);
      }
    }
  }
}
