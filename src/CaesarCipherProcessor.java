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

    public void process(String filePath, String commandStr, String key) {
        try {
            Command command = Command.fromString(commandStr);
            validateKey(command, key);
            String content = IOFile.read(filePath);
            String outputFilePath = getOutputFilePath(filePath, command);
            if (command == Command.BRUTEFORCE) {
                outputFilePath = filePath.replace(".txt", "_bruteforced");
                Scanner scanner = new Scanner(System.in);
                System.out.println("For brute-force with enumeration press 1, for frequency analysis of letters press - 2:");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    bruteForce.attemptDecryption(content, outputFilePath);
                }
                if (choice == 2) {
                    bruteForce.automaticalyBrut(content, outputFilePath);
                }
            } else {
                int shift;
                try {
                    shift = Integer.parseInt(key);
                } catch (NumberFormatException e) {
                    throw CaesarCipherException.invalidKey();
                }
                String result = command == Command.ENCRYPT ? cipher.encrypt(content, shift) : cipher.decrypt(content, shift);
                IOFile.write(outputFilePath, result);
                System.out.println(command == Command.ENCRYPT ? "Encrypted to: " + outputFilePath : "Decrypted to в: " + outputFilePath);
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        } catch (CaesarCipherException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateKey(Command command, String key) {
        if (command.requiresKey() && (key == null || key.isEmpty())) {
            throw CaesarCipherException.missingKey(command.getValue());
        }
    }

    private String getOutputFilePath(String filePath, Command command) {
        if (command == Command.BRUTEFORCE) {
            return filePath.replace(".txt", "bruteforce");
        }
        return filePath.replace(".txt", "_" + command.getValue() + "ed.txt");
    }
}
