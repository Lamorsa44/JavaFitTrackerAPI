package fitnesstracker.entity.fitapp;

public record FitAppResponse(String name, String apikey, String category) {
    public FitAppResponse(FitnessApp fitnessApp) {
        this(fitnessApp.getName(),
                fitnessApp.getApikey().toString(),
                fitnessApp.getCategory().toString());
    }
}
