// Главный класс
public class Main {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("To run the program: java -jar FinalChapter1.jar <path_to_file> <command> [key]");
            System.out.println("Commands: encrypt, decrypt, bruteforce");
            System.out.println("A key is required for encryption or decryption");
            return;
        }
        String filePath = args[0];
        String command = args[1];
        String key = args.length > 2 ? args[2] : null;
        CaesarCipherProcessor processor = new CaesarCipherProcessor();
        processor.process(filePath, command, key);
    }
}