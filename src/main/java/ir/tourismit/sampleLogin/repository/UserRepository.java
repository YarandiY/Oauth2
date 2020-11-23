package ir.tourismit.sampleLogin.repository;

import ir.tourismit.sampleLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByUsername(String username);

    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUsername(String username);
    User save(User s);
}
