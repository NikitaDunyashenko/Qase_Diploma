package enums;

public enum Type {

    OTHER("Other"), FUNCTIONAL("Functional"), SMOKE("Smoke"), REGRESSION("Regression"),
    SECURITY("Security"), USABILITY("Usability"), PERFORMANCE("Performance"),
    ACCEPTANCE("Acceptance"), COMPATIBILITY("Compatibility"), INTEGRATION("Integration"),
    EXPLORATORY("Exploratory");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Type fromString(String name) {
        for (Type type : Type.values()) {
            if(type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}
