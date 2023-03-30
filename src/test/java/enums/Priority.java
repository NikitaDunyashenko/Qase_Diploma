package enums;

public enum Priority {
    NOT_SET("Not set"), HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Priority fromString(String value) {
        for (Priority priority : Priority.values()) {
            if (priority.getName().equals(value)) {
                return priority;
            }
        }
        return null;
    }
}
