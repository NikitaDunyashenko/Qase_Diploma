package enums;

public enum IsFlaky {

    YES("Yes"), NO("No");

    private final String name;

    IsFlaky(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static IsFlaky fromString(String name) {
        for(IsFlaky isFlaky : IsFlaky.values()) {
            if(isFlaky.getName().equals(name)) {
                return isFlaky;
            }
        }
        return null;
    }
}
