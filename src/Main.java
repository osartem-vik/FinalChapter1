import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        Writer writer = new Writer();
        CaesarCipher cipher = new CaesarCipher();
        BruteForcer bruteForce = new BruteForcer(scanner);


        System.out.println("Введите путь к файлу:");
        String filePath = scanner.nextLine();

        System.out.println("Выберите операцию:");
        System.out.println("1 - Шифрование");
        System.out.println("2 - Дешифрование с ключом");
        System.out.println("3 - Брутфорс дешифрование");
        int operation = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        try {
            String content = reader.read(filePath);
            String outputFilePath;

            switch (operation) {
                case 1: // Шифрование
                    System.out.println("Введите ключ для шифрования:");
                    int encryptShift = scanner.nextInt();
                    String encrypted = cipher.encrypt(content, encryptShift);
                    outputFilePath = filePath.replace(".txt", "_encrypted.txt");
                    writer.write(outputFilePath, encrypted);
                    System.out.println("Зашифровано в: " + outputFilePath);
                    break;

                case 2: // Дешифрование с ключом
                    System.out.println("Введите ключ для дешифрования:");
                    int decryptShift = scanner.nextInt();
                    String decrypted = cipher.decrypt(content, decryptShift);
                    outputFilePath =filePath.replace(".txt", "_decrypted.txt");
                    writer.write(outputFilePath, decrypted);
                    System.out.println("Расшифровано в: " + outputFilePath);
                    break;
                case 3: // Брутфорс
                    outputFilePath = filePath.replace(".txt", "_bruteforce");
                    bruteForce.attemptDecryption(content, outputFilePath);
                    break;

                default:
                    System.out.println("Неверная операция");
            }

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}