package enums;

public enum ParentSuite {

    PROJECT_ROOT("Project root");

    private final String name;

    ParentSuite(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ParentSuite fromString(String value) {
        for (ParentSuite parentSuite : ParentSuite.values()) {
            if (parentSuite.getName().equals(value)) {
                return parentSuite;
            }
        }
        return null;
    }
}
