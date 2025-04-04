import java.io.IOException;
import java.util.Scanner;

public class BruteForcer {
    private final CaesarCipher cipher;
    private final Scanner scanner;

    public BruteForcer(Scanner scanner) {
        this.cipher = new CaesarCipher();
        this.scanner = scanner;
    }

    public void attemptDecryption(String encryptedText, String outputPath) {
        Writer writer = new Writer();

        for (int shift = 0; shift < 26; shift++) {
            String decrypted = cipher.decrypt(encryptedText, shift);
            String[] lines = decrypted.split("\n");

            // Выводим первые 3 строки (или меньше, если их меньше)
            System.out.println("\nВариант с ключом " + shift + ":");
            for (int i = 0; i < Math.min(3, lines.length); i++) {
                System.out.println(lines[i]);
            }

            // Запрашиваем подтверждение
            System.out.println("Сохранить этот вариант? (1 - да, 0 - нет)");
            int save = scanner.nextInt();

            if (save == 1) {
                String filePath = outputPath + "_shift" + shift + ".txt";
                try {
                    writer.write(filePath, decrypted);
                    System.out.println("Сохранено в: " + filePath);
                    break; // Прерываем цикл после сохранения
                } catch (IOException e) {
                    System.out.println("Ошибка при сохранении: " + e.getMessage());
                }
            }
        }
        System.out.println("Брутфорс завершен");
    }
}