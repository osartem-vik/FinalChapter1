import java.io.FileWriter;
import java.io.IOException;

class BruteForcer {
    private final CaesarCipher cipher;

    public BruteForcer() {
        this.cipher = new CaesarCipher();
    }

    public void attemptDecryption(String encryptedText, String outputPath) {
        try {
            Writer writer = new Writer();
            for (int shift = 0; shift < 26; shift++) {
                String decrypted = cipher.decrypt(encryptedText, shift);
                String filePath = outputPath + "_shift" + shift + ".txt";
                writer.write(filePath, decrypted);
                System.out.println("Сохранен вариант с ключом " + shift + ": " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при брутфорсе: " + e.getMessage());
        }
    }
}