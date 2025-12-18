package fitnesstracker.repository;

import fitnesstracker.entity.data.FitnessData;
import fitnesstracker.entity.fitapp.FitnessApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FitnessDataRepository extends JpaRepository<FitnessData, Long> {
    List<FitnessData> findByApplication(FitnessApp application);
}
