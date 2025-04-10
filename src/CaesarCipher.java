public class CaesarCipher {
    private String processText(String text, int shift) {

        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                if (character >= 65 && character <= 122) {
                    char base = Character.isUpperCase(character) ? 'A' : 'a';
                    int originalPosition = character - base;
                    int newPosition = (originalPosition + shift) % 26;
                    if (newPosition < 0) newPosition += 26; // For correct encryption
                    char newCharacter = (char) (base + newPosition);
                    result.append(newCharacter);
                }
                if (character >= 1024 && character <= 1279) {
                    char base = Character.isUpperCase(character) ? 'А' : 'а';
                    int originalPosition = character - base;
                    int newPosition = (originalPosition + shift) % 32;
                    if (newPosition < 0) newPosition += 32; // For correct encryption
                    char newCharacter = (char) (base + newPosition);
                    result.append(newCharacter);
                }
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