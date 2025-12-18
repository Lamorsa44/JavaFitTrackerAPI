package fitnesstracker.entity.data;

public record FitnessRequest(String username, String activity,
                             int duration, int calories) {
    public FitnessRequest {
        username = username.toLowerCase();
    }
}
