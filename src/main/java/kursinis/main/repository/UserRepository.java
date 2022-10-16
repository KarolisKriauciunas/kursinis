package kursinis.main.repository;

import kursinis.main.model.domain.Account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserName(String username);
    User findUserByEmployeeID(Long Id);
}
