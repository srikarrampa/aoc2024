
package day_13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC13_1 {

  public static long sum = 0;
  public static void main(String[] args) {
    String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-13.txt";

    Input input = new Input();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.contains("Button A")) {
          String[] value = line.split(",");
          input.buttonAx = Long.parseLong(value[0].split("\\+")[1]);
          input.buttonAy = Long.parseLong(value[1].split("\\+")[1]);
        } else if (line.contains("Button B"))  {
          String[] value = line.split(",");
          input.buttonBx = Long.parseLong(value[0].split("\\+")[1]);
          input.buttonBy = Long.parseLong(value[1].split("\\+")[1]);
        } else if (line.contains("Prize")) {
          String[] value = line.split(",");
          input.prizeX = Long.parseLong(value[0].split("=")[1]);
          input.prizeY = Long.parseLong(value[1].split("=")[1]);
        } else {
          long num = input.prizeX * input.buttonAy - input.prizeY* input.buttonAx;
          long den = input.buttonBx * input.buttonAy - input.buttonBy* input.buttonAx;
          long b = num/den;
          if (num % den != 0) {
            continue;
          }
          num = input.prizeX * input.buttonBy - input.prizeY* input.buttonBx;
          den = input.buttonAx * input.buttonBy - input.buttonAy* input.buttonBx;
          long a = num / den;
          if (num % den != 0) {
            continue;
          }
          sum += (a < 100 && b < 100) ? 3 * a + b : 0;
          input = new Input();
        }
      }



    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }

    System.out.println("Count: " + sum);
  }

  static class Input {
    long buttonAx;
    long buttonAy;
    long buttonBx;
    long buttonBy;
    long prizeX;
    long prizeY;
    
    public Input() {
      
    }
  }
}
