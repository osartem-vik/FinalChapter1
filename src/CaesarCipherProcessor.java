// Класс, содержащий всю логику обработки
import java.io.IOException;

public class CaesarCipherProcessor {
    private final Reader reader;
    private final Writer writer;
    private final CaesarCipher cipher;
    private final BruteForcer bruteForce;
    public CaesarCipherProcessor() {
        this.reader = new Reader();
        this.writer = new Writer();
        this.cipher = new CaesarCipher();
        this.bruteForce = new BruteForcer();
    }
    public void process (String filePath, String command, String key){
        try {
            String content = reader.read(filePath);
            String outputFilePath;
            switch (command.toLowerCase()) {
                case "encrypt":
                    if (key == null || key.isEmpty()) {
                        throw new IllegalArgumentException("Ключ обязателен для шифрования");
                    }
                    int encryptShift = Integer.parseInt(key);
                    String encrypted = cipher.encrypt(content, encryptShift);
                    outputFilePath = filePath.replace(".txt", "_encrypted.txt");
                    writer.write(outputFilePath, encrypted);
                    System.out.println("Зашифровано в: " + outputFilePath);
                    break;
                case "decrypt":
                    if (key == null || key.isEmpty()) {
                        throw new IllegalArgumentException("Ключ обязателен для дешифрования");
                    }
                    int decryptShift = Integer.parseInt(key);
                    String decrypted = cipher.decrypt(content, decryptShift);
                    outputFilePath = filePath.replace(".txt", "_decrypted.txt");
                    writer.write(outputFilePath, decrypted);
                    System.out.println("Расшифровано в: " + outputFilePath);
                    break;
                case "bruteforce":
                    outputFilePath = filePath.replace(".txt", "_bruteforce");
                    bruteForce.attemptDecryption(content, outputFilePath);
                    break;
                default:
                    throw new IllegalArgumentException("Неверная команда. Используйте: encrypt, decrypt или bruteforce");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}