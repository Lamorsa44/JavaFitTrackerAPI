package fitnesstracker.repository;

import fitnesstracker.entity.user.FitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FitUserRepository extends JpaRepository<FitUser, Long> {
    boolean existsFitUserByEmail(String email);
    Optional<FitUser> findByEmail(String email);
}
