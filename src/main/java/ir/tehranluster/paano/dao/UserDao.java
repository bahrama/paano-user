package ir.tehranluster.paano.dao;

import ir.tehranluster.paano.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> ,CustomUserDao{

    Optional<User> findUserByEmail(String email);
}
