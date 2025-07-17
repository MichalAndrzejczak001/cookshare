package spring.project.cookshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.project.cookshare.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
