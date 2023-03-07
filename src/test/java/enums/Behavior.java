package enums;

public enum Behavior {

    NOT_SET("Not set"), POSITIVE("Positive"), NEGATIVE("Negative"), DESTRUCTIVE("Desctructive");

    private final String name;

    Behavior(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Behavior fromString(String name) {
        for(Behavior behavior : Behavior.values()) {
            if(behavior.getName().equals(name)) {
                return behavior;
            }
        }
        return null;
    }
}
