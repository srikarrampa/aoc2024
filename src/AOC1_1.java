import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AOC1_1 {
    public static void main(String[] args) {
        String filePath = "/Users/srampally/Documents/code/aoc2024/src/test/aoc-1.txt";
        List<Integer> dataList1 = new ArrayList<>();
        List<Integer> dataList2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    dataList1.add(Integer.parseInt(parts[0]));
                    dataList2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Sum: " + findDistance(dataList1, dataList2));

    }

    private static int findDistance(List<Integer> dataList1, List<Integer> dataList2) {
        Collections.sort(dataList1);
        Collections.sort(dataList2);
        int sum = 0;
        for (int i = 0; i < dataList1.size(); i++) {
          sum += Math.abs(dataList1.get(i) - dataList2.get(i));
        }

        return sum;
    }
}