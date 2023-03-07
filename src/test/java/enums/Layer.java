package enums;

public enum Layer {

    E2E("E2E"), API("API"), UNIT("Unit");

    private final String name;

    Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Layer fromString(String name) {
        for (Layer layer : Layer.values()) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }
}
