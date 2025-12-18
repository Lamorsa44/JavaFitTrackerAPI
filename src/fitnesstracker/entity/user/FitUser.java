package fitnesstracker.entity.user;

import fitnesstracker.entity.fitapp.FitnessApp;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FitUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany
    private List<FitnessApp> applications;

    public FitUser() {}
    public FitUser(FitUserRequest fitUserRequest) {
        this.email = fitUserRequest.email();
        this.password = fitUserRequest.password();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FitnessApp> getApplications() {
        return applications;
    }

    public void setApplications(List<FitnessApp> applications) {
        this.applications = applications;
    }
}
