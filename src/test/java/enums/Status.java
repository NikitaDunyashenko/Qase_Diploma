package enums;

public enum Status {

    ACTUAL("Actual"), DRAFT("Draft"), DEPRECATED("Deprecated");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Status fromString(String value) {
        for (Status status : Status.values()) {
            if(status.getName().equals(value)){
                return status;
            }
        }
        return null;
    }
}
