public class CaesarCipher {
    private String processText(String text, int shift) {

        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                if (character >= Constants.FIRST_LETTER_LAT && character <= Constants.LAST_LETTER_LAT) {
                    char base = Character.isUpperCase(character) ? Constants.FIRST_LETTER_LAT : Constants.FIRST_SMALL_LETTER_LAT;
                    int originalPosition = character - base;
                    int newPosition = (originalPosition + shift) % Constants.QUANTITY_LETTER_LAT;
                    if (newPosition < 0) newPosition += Constants.QUANTITY_LETTER_LAT;
                    char newCharacter = (char) (base + newPosition);
                    result.append(newCharacter);
                }
                if (character >= Constants.FIRST_LETTER_CYR && character <= Constants.LAST_LETTER_CYR) {
                    char base = Character.isUpperCase(character) ? Constants.FIRST_LETTER_CYR : Constants.FIRST_SMALL_LETTER_CYR;
                    int originalPosition = character - base;
                    int newPosition = (originalPosition + shift) % Constants.QUANTITY_LETTER_CYR;
                    if (newPosition < 0) newPosition += Constants.QUANTITY_LETTER_CYR;
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