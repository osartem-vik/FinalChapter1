import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BruteForcer {
    private final CaesarCipher cipher;
    private final int MOST_FREQ_LETTER_LAT = 101;
    private final int MOST_FREQ_LETTER_CYR = 1086;
    private final int QUANTITY_LETTER_CYR = 32;
    private final int QUANTITY_LETTER_LAT = 26;
    private final char FIRST_LETTER_LAT = 'A';
    private final char FIRST_SMALL_LETTER_LAT = 'a';
    private final char FIRST_LETTER_CYR = 'А';
    private final char FIRST_SMALL_LETTER_CYR = 'а';
    public BruteForcer() {
        this.cipher = new CaesarCipher();
    }


    public void attemptDecryption(String encryptedText, String outputPath) {
        IOFile IOFile = new IOFile();
        int quantAttempt = isLatinText(mostFrequencyChar(encryptedText)) ? QUANTITY_LETTER_LAT : QUANTITY_LETTER_CYR;
        for (int shift = 0; shift < quantAttempt; shift++) {
            String decrypted = cipher.decrypt(encryptedText, shift);
            String[] lines = decrypted.split("\n");
            System.out.println("\n A variant with a key " + shift + ":");
            for (int i = 0; i < Math.min(3, lines.length); i++) {
                System.out.println(lines[i]);
            }
            System.out.println("For save the variant press 1, to continue press any number: ");
            Scanner scanner = new Scanner(System.in);
            int save = scanner.nextInt();
            if (save == 1) {
                String filePath = outputPath + "_shift" + shift + ".txt";
                try {
                    IOFile.write(filePath, decrypted);
                    System.out.println("Saved to: " + filePath);
                    break;
                } catch (IOException e) {
                    System.out.println("Save error: " + e.getMessage());
                }
            }
        }
        System.out.println("Brute-force completed");
    }

    public char mostFrequencyChar(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        char mostFrequent = 0;
        int maxCount = 0;
        for (char ch : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(ch))
                frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequent = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFrequent;
    }
    private String stringProcessor(String encryptedText) {
        StringBuilder result = new StringBuilder();
        int shift = -1;
        char firstLet = ' ', firstLetSmall = ' ';
        int quantLetters = 0;
        if (isLatinText(mostFrequencyChar(encryptedText))) {
            shift = mostFrequencyChar(encryptedText) - MOST_FREQ_LETTER_LAT;
            firstLet = FIRST_LETTER_LAT;
            firstLetSmall = FIRST_SMALL_LETTER_LAT;
            quantLetters = QUANTITY_LETTER_LAT;
        } else if (isCyrilicText(mostFrequencyChar(encryptedText))) {
            shift = mostFrequencyChar(encryptedText) - MOST_FREQ_LETTER_CYR;
            firstLet = FIRST_LETTER_CYR;
            firstLetSmall = FIRST_SMALL_LETTER_CYR;
            quantLetters = QUANTITY_LETTER_CYR;
        }
        for (char character : encryptedText.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? firstLet : firstLetSmall;
                int originalPosition = character - base;
                int newPosition = (originalPosition - shift) % quantLetters;
                if (newPosition < 0) newPosition += quantLetters;
                char newCharacter = (char) (base + newPosition);
                result.append(newCharacter);
            } else result.append(character);

        }
        return result.toString();
    }

    private boolean isLatinText(char mostFreqChar) {
        return mostFreqChar >= 65 && mostFreqChar <= 122;
    }

    private boolean isCyrilicText(char mostFreqChar) {
        return mostFreqChar >= 1024 && mostFreqChar <= 1279;
    }

    public void automaticalyBrut(String encryptedText, String outputPath) {
        if (encryptedText == null || encryptedText.isEmpty()) {
            System.out.println("The file is empty");
            return;
        }
        IOFile IOFile = new IOFile();
        String result = stringProcessor(encryptedText);
        String filePath = outputPath + "_automaticaly.txt";
        try {
            IOFile.write(filePath, result);
            System.out.println("Saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Saving error: " + e.getMessage());
        }
    }
}
