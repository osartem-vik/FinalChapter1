import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

class IOFile {
    public String read(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    public void write(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
