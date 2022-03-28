import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileWriter {

    public void writeToFile(LinkedHashMap<String, ArrayList<String>> seatChart) {
        BufferedWriter writing;
        try {
            writing = new BufferedWriter(new java.io.FileWriter("output.txt"));
            for (Map.Entry<String, ArrayList<String>> pairs : seatChart.entrySet()) {
                String str = pairs.getKey() + " " + pairs.getValue();
                writing.write(str + "\n");
            }
            writing.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Path path = Paths.get("output.txt");
        System.out.println(path.toAbsolutePath());
    }
}