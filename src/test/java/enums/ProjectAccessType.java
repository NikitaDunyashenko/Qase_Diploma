package enums;

public enum ProjectAccessType {

    PRIVATE("private"), PUBLIC("public");

    private final String name;

    ProjectAccessType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ProjectAccessType fromString(String value) {
        for (ProjectAccessType projectAccessType : ProjectAccessType.values()) {
            if(projectAccessType.getName().equals(value)){
                return projectAccessType;
            }
        }
        return null;
    }
}
