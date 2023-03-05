package enums;

public enum Assignee {

    UNASSIGNED("Unassigned"), NIKITA_DUNYASHENKO("Nikita Dunyashenko");

    private final String name;

    Assignee(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Assignee fromString(String name) {
        for (Assignee assignee : Assignee.values()) {
            if(assignee.getName().equals(name)) {
                return assignee;
            }
        }
        return null;
    }
}
