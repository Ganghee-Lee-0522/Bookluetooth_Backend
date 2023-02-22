package cotato.Bookluetooth.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

<<<<<<< HEAD
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserEmail(String userEmail);

    Optional<Users> findByUserId(Users userId);
=======
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserEmail(String userEmail);
>>>>>>> honey
}
