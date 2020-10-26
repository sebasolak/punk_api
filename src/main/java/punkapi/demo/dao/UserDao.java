package punkapi.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import punkapi.demo.model.User;

@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query(value = "Select email from user WHERE login like ?1", nativeQuery = true)
    String selectUserEmailByLogin(String login);
}
