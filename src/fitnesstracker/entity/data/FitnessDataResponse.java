package fitnesstracker.entity.data;

public record FitnessDataResponse(Long id, String username,
                                  String activity, int duration,
                                  int calories, String application) {
    public FitnessDataResponse(FitnessData fitnessData) {
        this(fitnessData.getId(),
                fitnessData.getUsername(), fitnessData.getActivity(),
                fitnessData.getDuration(), fitnessData.getCalories(),
                fitnessData.getApplication().getName());
    }
}
