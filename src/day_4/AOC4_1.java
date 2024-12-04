
package day_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOC4_1 {

  public static int[][] DIRS = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,-0}, {-1, 1}};
  public static char[] XMAS = {'X', 'M', 'A', 'S'};
  static int count = 0;

  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-4.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      ArrayList<ArrayList<Character>> charMatrix = new ArrayList<>();
      while ((line = br.readLine()) != null) {
        char[] charArray = line.toCharArray();
        ArrayList<Character> tempArrayList = IntStream.range(0, charArray.length)
                .mapToObj(i -> charArray[i])
                .collect(Collectors.toCollection(ArrayList::new));
        charMatrix.add(tempArrayList);
      }

      calculateXmasCount(charMatrix);
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + count);
  }

  private static void calculateXmasCount(ArrayList<ArrayList<Character>> charMatrix) {;
    for (int i = 0; i < charMatrix.size(); i++) {
      for (int j = 0; j < charMatrix.get(i).size(); j++) {
        if (charMatrix.get(i).get(j) == 'X') {
          traverseAndGetCount(i, j, charMatrix, 1, -1);
        }
      }

    }
  }

  private static void traverseAndGetCount(int x, int y, ArrayList<ArrayList<Character>> charMatrix, int xmasIndex, int prev) {

    if (xmasIndex == XMAS.length) {
      count++;
      return;
    }

    if (prev == -1) {
      for (int i = 0; i < DIRS.length; i++) {
        int newX = x + DIRS[i][0];
        int newY = y + DIRS[i][1];
        if (newX < 0 ||
                newY < 0 ||
                newX >= charMatrix.size() ||
                newY >= charMatrix.get(0).size()) {
          continue;
        }

        if (charMatrix.get(newX).get(newY) == XMAS[xmasIndex])
          traverseAndGetCount(newX, newY, charMatrix, xmasIndex + 1, i);
      }
    } else {
      int newX = x + DIRS[prev][0];
      int newY = y + DIRS[prev][1];
      if (newX < 0 ||
              newY < 0 ||
              newX >= charMatrix.size() ||
              newY >= charMatrix.get(0).size()) {
        return;
      }

      if (charMatrix.get(newX).get(newY) == XMAS[xmasIndex])
        traverseAndGetCount(newX, newY, charMatrix, xmasIndex + 1, prev);
    }
  }
}
