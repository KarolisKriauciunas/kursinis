package kursinis.main.repository;

import kursinis.main.model.domain.Account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface
UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserName(String username);
    Optional<User> findUserByEmployeeID(Long Id);
    Optional <User> findByUserNameAndPassword(String username, String password);
}
