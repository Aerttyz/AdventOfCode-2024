import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        String pathFile = "day_3/src/input.txt";

        List<Integer> istructions = new ArrayList<>();
        Integer totalIstructions = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            StringBuilder newLine = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                newLine.append(line);                
            }
            String content = newLine.toString().replaceAll("(?s)don't\\(\\)(.*?)(do\\(\\)|$)", " ");
            Pattern pattern = Pattern.compile("mul\\((\\d+),\\s*(\\d+)\\)");
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                Integer mul = Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
                istructions.add(mul);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }

        for (int i = 0; i < istructions.size(); i++) {
            totalIstructions += istructions.get(i);
        }
        System.out.println("Result: " + totalIstructions);
    }
}
