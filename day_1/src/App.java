import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        String pathFile = "day_1/src/input.txt";

        List<Integer> leftIds = new ArrayList<>();
        List<Integer> rightIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] ids = line.trim().split("\\s+");
                if (ids.length > 0) {
                    leftIds.add(Integer.parseInt(ids[0]));
                }
                if (ids.length > 1) {
                    rightIds.add(Integer.parseInt(ids[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        // Collections.sort(leftIds);
        // Collections.sort(rightIds);

        // List<Integer> distances = distance(leftIds, rightIds);

        // System.out.println("Distances: " + distances);

        // System.out.println("Total distance: " +  totalSum(distances));


        System.out.println("Similarity: " + totalSimilarity(leftIds, rightIds));

    }

    public static List<Integer> distance(List<Integer> leftIds, List<Integer> rightIds) {
        List<Integer> distances = new ArrayList<>();

        for (int i = 0; i < leftIds.size(); i++) {
            for (int j = 0; j < rightIds.size(); j++) {
                if(i==j){
                    distances.add(Math.abs(leftIds.get(i) - rightIds.get(j)));
                }
            }
        }

        return distances;
    }

    // public static Integer totalSum(List<Integer> sum) {
    //     Integer total = 0;

    //     for (int i = 0; i < sum.size(); i++) {
    //         total += sum.get(i);
    //     }

    //     return total;
    // }

    public static Integer totalSimilarity(List<Integer> leftIds, List<Integer> rightIds){
        Set<Integer> rightIdsSet = new HashSet<>(rightIds);
        int similarity = 0;

        for(int i=0; i < leftIds.size(); i++){
            if(rightIdsSet.contains(leftIds.get(i))){
                similarity += leftIds.get(i) *  Collections.frequency(rightIds, leftIds.get(i));
            }
        }
        return similarity;
    }

}
