import java.io.IOException;

// Класс для брутфорс-атаки
class BruteForcer {
    private final CaesarCipher cipher;

    public BruteForcer() {
        this.cipher = new CaesarCipher();
    }

    public void attemptDecryption(String encryptedText, String outputPath) {
        Writer writer = new Writer();
        for (int shift = 0; shift < 26; shift++) {
            String decrypted = cipher.decrypt(encryptedText, shift);
            String[] lines = decrypted.split("\n");
            System.out.println("\nВариант с ключом " + shift + ":");
            for (int i = 0; i < Math.min(3, lines.length); i++) {
                System.out.println(lines[i]);
            }
            System.out.println("Сохранить этот вариант? (1 - да, 0 - нет)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int save = scanner.nextInt();
            if (save == 1) {
                String filePath = outputPath + "_shift" + shift + ".txt";
                try {
                    writer.write(filePath, decrypted);
                    System.out.println("Сохранено в: " + filePath);
                    break;
                } catch (IOException e) {
                    System.out.println("Ошибка при сохранении: " + e.getMessage());
                }
            }
        }
        System.out.println("Брутфорс завершен");
    }
}