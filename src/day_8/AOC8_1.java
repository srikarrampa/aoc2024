
package day_8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC8_1 {

  public static ArrayList<char[]> input = new ArrayList<>();
  public static HashSet<String> antinodes = new HashSet<>();
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-8.txt";

    HashMap<Character, ArrayList<int[]>> locations = new HashMap<>();


    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int row = 0;
      while ((line = br.readLine()) != null) {
        input.add(line.toCharArray());
        for (int i = 0; i < line.length(); i++) {
          if (line.charAt(i) != '.') {
            locations.computeIfAbsent(line.charAt(i), k -> new ArrayList<>()).add(new int[]{row, i});
          }
        }
        row++;
      }
      for (Map.Entry<Character, ArrayList<int[]>> entry : locations.entrySet()) {
        calulateAntinodes(entry.getValue(), row - 1, input.get(0).length - 1);
      }


    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Sum: " + antinodes.size());
  }

    private static void calulateAntinodes(ArrayList<int[]> locations, int maxRow, int maxCol) {
    for (int i = 0; i < locations.size() - 1; i++) {
      for (int j = i + 1; j < locations.size(); j++) {
          int[] firstLoc = locations.get(i);
          int[] secondLoc = locations.get(j);

          int diffX = firstLoc[0] - secondLoc[0];
          int diffY = firstLoc[1] - secondLoc[1];

          int newX = firstLoc[0] + diffX;
          int newY = firstLoc[1] + diffY;
          while (newX >= 0 && newX <= maxRow && newY >= 0 && newY <= maxCol) {
            antinodes.add(newX + "," + newY);
            newX = newX + diffX;
            newY = newY + diffY;
          }

          newX = secondLoc[0] - diffX;
          newY = secondLoc[1] - diffY;
          while (newX >= 0 && newX <= maxRow && newY >= 0 && newY <= maxCol) {
            antinodes.add(newX + "," + newY);
            newX = newX - diffX;
            newY = newY - diffY;
          }

      }
    }
  }
}
