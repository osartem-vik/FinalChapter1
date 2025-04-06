import java.io.BufferedWriter;
import java.io.IOException;

public class Writer {
    public void write(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
