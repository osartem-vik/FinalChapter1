// Главный класс
public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java Main <путь_к_файлу> <команда> [ключ]");
            System.out.println("Команды: encrypt, decrypt, bruteforce");
            System.out.println("Ключ обязателен для encrypt и decrypt");
            return;
        }
        String filePath = args[0];
        String command = args[1];
        String key = args.length > 2 ? args[2] : null;
        CaesarCipherProcessor processor = new CaesarCipherProcessor();
        processor.process(filePath, command, key);
    }
}