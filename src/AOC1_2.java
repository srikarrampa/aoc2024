import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AOC1_2 {

    public static void main(String[] args) {
        String filePath = "/Users/srampally/Documents/code/AdventOfCode/src/test/aoc-1.txt";
        HashMap<Integer, Integer> dataMap1 = new HashMap<>();
        HashMap<Integer, Integer> dataMap2 = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split each line into two integers and store in a list
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    int number1 = Integer.parseInt(parts[0]);
                    dataMap1.put(number1, dataMap1.getOrDefault(number1, 0) + 1);
                    int number2 = Integer.parseInt(parts[1]);
                    dataMap2.put(number2, dataMap2.getOrDefault(number2, 0) + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Sum: " + findSimilarity(dataMap1, dataMap2));

    }

    private static int findSimilarity(HashMap<Integer, Integer> dataMap1, HashMap<Integer, Integer> dataMap2) {
        int similarityScore = 0;
        for (Integer key: dataMap1.keySet()) {
            similarityScore += key * dataMap1.get(key) * dataMap2.getOrDefault(key, 0);
        }

        return similarityScore;
    }
}