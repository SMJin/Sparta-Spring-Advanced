package sparta.spring.springadvanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.spring.springadvanced.model.ApiUseTime;
import sparta.spring.springadvanced.model.User;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime, Long> {
    Optional<ApiUseTime> findByUser(User user);
}
