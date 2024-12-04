
package day_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AOC4_2 {

  public static int[][] DIRS = {{1,1}, {1,-1}, {-1,-1}, {-1, 1}};
  public static char[][][] XMAS = {{
          {'M', '.', 'S'},
          {'.', 'A', ','},
          {'M', '.', 'S'}},

          {
          {'M', '.', 'M'},
          {'.', 'A', ','},
          {'S', '.', 'S'}},

          {
          {'S', '.', 'S'},
          {'.', 'A', ','},
          {'M', '.', 'M'}},

          {
          {'S', '.', 'M'},
          {'.', 'A', ','},
          {'S', '.', 'M'}}};
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
        if (charMatrix.get(i).get(j) == 'A') {
          traverseAndGetXmasCount(i, j, charMatrix);
        }
      }

    }
  }

  private static void traverseAndGetXmasCount(int i, int j, ArrayList<ArrayList<Character>> charMatrix) {

    for (char[][] box : XMAS) {
      if (xmasMatch(i, j, box, charMatrix)) {
        count++;
        return;
      }
    }

  }

  private static boolean xmasMatch(int x, int y, char[][] box, ArrayList<ArrayList<Character>> charMatrix) {

    for (int[] dir : DIRS) {
      int newX = x + dir[0];
      int newY = y + dir[1];
      if (newX < 0 ||
              newY < 0 ||
              newX >= charMatrix.size() ||
              newY >= charMatrix.get(0).size()) {
        return false;
      }

      if (charMatrix.get(newX).get(newY) != box[dir[0] + 1][dir[1] + 1])
        return false;

    }
    return true;
  }
}
