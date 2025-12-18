package fitnesstracker.entity.data;

import fitnesstracker.entity.fitapp.FitnessApp;
import jakarta.persistence.*;

@Entity
public class FitnessData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String activity;
    private int duration;
    private int calories;
    @ManyToOne
    private FitnessApp application;

    public FitnessData() {}

    public FitnessData(FitnessRequest fitnessRequest, FitnessApp application) {
        this.username = fitnessRequest.username();
        this.activity = fitnessRequest.activity();
        this.duration = fitnessRequest.duration();
        this.calories = fitnessRequest.calories();
        this.application = application;
    }
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public FitnessApp getApplication() {
        return application;
    }

    public void setApplication(FitnessApp application) {
        this.application = application;
    }
}
