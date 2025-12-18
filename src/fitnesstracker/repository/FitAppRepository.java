package fitnesstracker.repository;

import fitnesstracker.entity.fitapp.FitnessApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FitAppRepository extends JpaRepository<FitnessApp, Long> {
    boolean existsByNameIgnoreCase(String name);
    Optional<FitnessApp> findByApikey(UUID apikey);
}
