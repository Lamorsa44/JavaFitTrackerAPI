package fitnesstracker.entity.fitapp;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class FitnessApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, unique = true)
    private UUID apikey;
    private FitAppCategory category;

    public FitnessApp() {}
    public FitnessApp(FitAppRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.category = request.parseCategory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getApikey() {
        return apikey;
    }

    public void setApikey(UUID apikey) {
        this.apikey = apikey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public FitAppCategory getCategory() {
        return category;
    }

    @JsonGetter(value = "category")
    public String getCategoryLowerCase() {
        return getCategory().toString();
    }

    public void setCategory(FitAppCategory category) {
        this.category = category;
    }

    @PrePersist
    public void prePersist() {
        if (apikey == null) this.apikey = UUID.randomUUID();
    }
}
