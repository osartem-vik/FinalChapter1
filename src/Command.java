enum Command {
    ENCRYPT("encrypt", true),
    DECRYPT("decrypt", true),
    BRUTEFORCE("bruteforce", false);
    private final String value;
    private final boolean requiresKey;

    Command(String value, boolean requiresKey) {
        this.value = value;
        this.requiresKey = requiresKey;
    }

    public String getValue() {
        return value;
    }

    public boolean requiresKey() {
        return requiresKey;
    }

    public static Command fromString(String str) {
        for (Command command : values()) {
            if (command.value.equalsIgnoreCase(str)) {
                return command;
            }
        }
        throw CaesarCipherException.invalidCommand();
    }
}
