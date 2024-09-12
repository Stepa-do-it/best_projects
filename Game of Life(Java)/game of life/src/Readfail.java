import java.io.FileReader;
import java.io.BufferedReader;//для чтения строк из файла.
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Readfail {
    public char[][] charArray;
    public void ReadArray(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            int numRows = lines.size();
            int numCols = lines.get(0).length();
            charArray = new char[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    charArray[i][j] = lines.get(i).charAt(j);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



