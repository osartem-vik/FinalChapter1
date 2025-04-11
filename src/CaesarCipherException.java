public class CaesarCipherException extends RuntimeException{
    public CaesarCipherException(String message) {
        super(message);
    }
    public static CaesarCipherException invalidCommand(){
        return new CaesarCipherException("Invalid command. Use: encrypt, decrypt or bruteforce");
    }
    public static CaesarCipherException missingKey(String command){
        return new CaesarCipherException("A key is required for"+command);
    }
    public static CaesarCipherException invalidKey(){
        return new CaesarCipherException("A key must be numeric");
    }
}
