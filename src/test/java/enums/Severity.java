package enums;

public enum Severity {

    NOT_SET("Not set"), BLOCKER("Blocker"), CRITICAL("Critical"), MAJOR("Major"),
    NORMAL("Normal"), MINOR("Minor"), TRIVIAL("Trivial");

    private final String name;

    Severity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Severity fromString(String value) {
        for (Severity severity : Severity.values()) {
            if(severity.getName().equals(value)){
                return severity;
            }
        }
        return null;
    }

}
