import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String pathFile = "day_4/src/input.txt";

        List<String> lines = new ArrayList<>();
        int n = 0;
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                if (line.length() > n) {
                    n = line.length();
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }

        int m = lines.size();
        char[][] matriz = new char[m][n];

        for (int i = 0; i < m; i++) {
            String line = lines.get(i);
            for (int j = 0; j < n; j++) {
                matriz[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == 'A') {
                    if (isMas(matriz, i, j)) {
                        count++;
                    }
                }
            }
        }
        System.out.println("Result: " + count);
    }

    public static boolean isMas(char[][] matriz, int x, int y) {
        
        if(x <= 0 || x >= (matriz.length - 1) || y <= 0 || y >= (matriz[0].length - 1)){
            return false;
        }
        if (matriz[x + 1][y + 1] == 'S' && matriz[x - 1][y - 1] == 'M'
                || matriz[x - 1][y - 1] == 'S' && matriz[x + 1][y + 1] == 'M') {
            if (matriz[x + 1][y - 1] == 'S' && matriz[x - 1][y + 1] == 'M'
                    || matriz[x - 1][y + 1] == 'S' && matriz[x + 1][y - 1] == 'M') {
                return true;
            }
        }
        return false;
    }

    public static Integer isXmas(char[][] matriz, int x, int y) {
        int[][] directions = {
                { 1, 0 },
                { 0, 1 },
                { 1, 1 },
                { 1, -1 },
                { -1, 0 },
                { 0, -1 },
                { -1, -1 },
                { -1, 1 }
        };

        int count = 0;

        for (int i = 0; i < directions.length; i++) {
            int[] direction = directions[i];
            int stepX = direction[0];
            int stepY = direction[1];

            String word = "XMAS";

            boolean found = true;

            int currentX = x;
            int currentY = y;

            for (int j = 1; j < 4; j++) {
                currentX += stepX;
                currentY += stepY;
                if (currentX < 0 || currentX >= matriz.length || currentY < 0 || currentY >= matriz[0].length) {
                    found = false;
                    break;
                }
                if (!(matriz[currentX][currentY] == word.charAt(j))) {
                    found = false;
                    break;
                }
            }
            if (found) {
                count++;
            }
        }
        return count;
    }
}
