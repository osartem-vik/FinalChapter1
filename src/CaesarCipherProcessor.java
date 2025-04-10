import java.io.IOException;
import java.util.Scanner;

public class CaesarCipherProcessor {
    private final IOFile IOFile;
    private final CaesarCipher cipher;
    private final BruteForcer bruteForce;

    public CaesarCipherProcessor() {
        this.IOFile = new IOFile();
        this.cipher = new CaesarCipher();
        this.bruteForce = new BruteForcer();
    }

    public void process(String filePath, String command, String key) {
        try {
            String content = IOFile.read(filePath);
            String outputFilePath;
            switch (command.toLowerCase()) {
                case "encrypt":
                    if (key == null || key.isEmpty()) {
                        throw new IllegalArgumentException("A key is required for encryption");
                    }
                    int encryptShift = Integer.parseInt(key);
                    String encrypted = cipher.encrypt(content, encryptShift);
                    outputFilePath = filePath.replace(".txt", "_encrypted.txt");
                    IOFile.write(outputFilePath, encrypted);
                    System.out.println("Encrypted to: " + outputFilePath);
                    break;
                case "decrypt":
                    if (key == null || key.isEmpty()) {
                        throw new IllegalArgumentException("A key is required for decryption");
                    }
                    int decryptShift = Integer.parseInt(key);
                    String decrypted = cipher.decrypt(content, decryptShift);
                    outputFilePath = filePath.replace(".txt", "_decrypted.txt");
                    IOFile.write(outputFilePath, decrypted);
                    System.out.println("Decrypted to: " + outputFilePath);
                    break;
                case "bruteforce":
                    outputFilePath = filePath.replace(".txt", "_bruteforced");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("For brute-force with enumeration press 1, for frequency analysis of letters press - 2:");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        bruteForce.attemptDecryption(content, outputFilePath);
                        break;
                    }
                    if (choice == 2) {
                        bruteForce.automaticalyBrut(content, outputFilePath);
                        break;
                    }
                default:
                    throw new IllegalArgumentException("Invalid command. Use: encrypt, decrypt or bruteforce");
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The key must be numeric: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Argument input error: " + e.getMessage());
        }
    }
}