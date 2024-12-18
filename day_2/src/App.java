import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String pathFile = "day_2/src/input.txt";

        List<List<Integer>> report = new ArrayList<>();
        Integer result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break;
                }
                String[] reportIsSafe = line.trim().split("\\s+");
                List<Integer> lineReport = new ArrayList<>();

                for (String reportSafe : reportIsSafe) {
                    try {
                        lineReport.add(Integer.parseInt(reportSafe));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing number" + reportSafe);
                    }
                }
                report.add(lineReport);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        for (List<Integer> reportSafe : report) {
            if (diff(reportSafe)) {
                result++;
            }
        }

        // System.out.println("Report: " + report);
        System.out.println("Result: " + result);
    }

    public static boolean isSafe(List<Integer> report) {
        Integer direction = 0;
        for (int i = 0; i < report.size() - 1; i++) {
            Integer currentDirection = 0;
            if (report.get(i) > report.get(i + 1)) {
                currentDirection = 1;
            } else {
                currentDirection = -1;
            }
            if (direction == 0) {
                direction = currentDirection;
            } else if (direction != currentDirection) {
                return false;
            }

            if (!(Math.abs(report.get(i) - report.get(i + 1)) >= 1
                    && Math.abs(report.get(i) - report.get(i + 1)) <= 3)) {
                return false;
            }
        }
        return true;
    }

    public static boolean diff(List<Integer> report) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (int i = 0; i < report.size(); i++) {
            leftList = report.subList(0, i);
            rightList = report.subList(i + 1, report.size());
            List<Integer> newList = new ArrayList<>(leftList);
            newList.addAll(rightList);
            if (isSafe(newList)) {
                System.out.println("New List: " + newList);
                return true; 
            }
        }
        return false;
    }

}
