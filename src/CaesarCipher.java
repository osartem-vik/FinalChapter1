public class CaesarCipher {
    private String processText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int originalPosition = character - base;
                int newPosition = (originalPosition + shift) % 26;
                if (newPosition < 0) newPosition += 26; // Для корректного дешифрования
                char newCharacter = (char) (base + newPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    public String encrypt(String text, int shift) {
        return processText(text, shift);
    }

    public String decrypt(String text, int shift) {
        return processText(text, -shift);
    }
}