package fitnesstracker.entity.fitapp;

public enum FitAppCategory {
    BASIC, PREMIUM;

    public static FitAppCategory parse(String value) {
        return switch (value.toUpperCase()) {
            case "BASIC" -> BASIC;
            case "PREMIUM" -> PREMIUM;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
