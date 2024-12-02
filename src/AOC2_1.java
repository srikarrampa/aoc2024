import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AOC2_1 {

    public static void main(String[] args) {
        String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-2.txt";
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.trim().split(" ");
                List<Integer> arrayList = Arrays.stream(parts).map(Integer::parseInt).collect(Collectors.toList());
                if (safeList(arrayList, null)) {
                    count++;
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Count: " + count);

    }

    private static boolean safeList(List<Integer> arrayList, Boolean asc) {
        for (int i = 1; i < arrayList.size(); i++) {
            int secondLastNumber = arrayList.get(i - 1);
            int lastNumber = arrayList.get(i);
            if (lastNumber > secondLastNumber) {
                if (asc == null) {
                    asc = true;
                }

                if (!asc) {
                    break;
                } else {
                    int diff = lastNumber - secondLastNumber;
                    if (diff > 3) {
                        break;
                    }
                }
            } else if (secondLastNumber > lastNumber) {
                if (asc == null) {
                    asc = false;
                }
                if (asc) {
                    break;
                } else {
                    int diff = secondLastNumber - lastNumber;
                    if (diff > 3) {
                        break;
                    }
                }
            } else {
                break;
            }
            if (i == arrayList.size() - 1) {
                return true;
            }
        }
        return false;
    }
}