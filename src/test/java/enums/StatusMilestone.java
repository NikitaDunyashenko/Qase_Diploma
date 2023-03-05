package enums;

public enum StatusMilestone {

    ACTIVE("Active"), COMPLETED("Completed");

    private String name;

    StatusMilestone(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static StatusMilestone fromString(String name) {
        for(StatusMilestone statusMilestone : StatusMilestone.values()) {
            if(statusMilestone.getName().equals(name)) {
                return statusMilestone;
            }
        }
        return null;
    }
}
