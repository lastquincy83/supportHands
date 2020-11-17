package com.supporthands.supportHands.repository;

import com.supporthands.supportHands.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
@NamedQuery(name = "UserRepository.findByUsernameIgnoreCase", query = "SELECT u from User u where UPPER(User.username) = UPPER(?1)")
@Table(name = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByMobileNumber(@NotNull String mobileNumber);

    List<User> findByUsernameIgnoreCase(String username);

    User findUserByUserId(int userId);
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<User> getAllUserNames() {
//        List<User> userNameList = new ArrayList<>();
//        userNameList.addAll(jdbcTemplate.query("SELECT * from user;", new BeanPropertyRowMapper<>(User.class)));
//        return userNameList;
//    }

//    @Override
//    public int createUser(String firstName, String surname, String emailAddress, String mobileNumber, String status, String username, String password) {
//        int result;
//        result = jdbcTemplate.update("insert into user (firstname, surname, emailaddress, mobilenumber, status, username, password) values ('" + firstName + "', '" + surname + "', '" + emailAddress + "', '" + mobileNumber + "', '" + status + "', '" + username + "', '" + password + "')");
//        return result;
//    }
//
//    public int updateUserStatus(int userId, String status) {
//        int result;
//        result = jdbcTemplate.update("update user set status = '" + status + "' where userid = " + userId);
//        return result;
//    }
//
//    public User getUser(int userId) {
//        User user;
//        try {
//            if (userId > 0){
//                user = jdbcTemplate.queryForObject("SELECT * from user where userid = " + userId, new BeanPropertyRowMapper<>(User.class));
//            } else user = null;
//        } catch (Exception e) {
//            return null;
//        }
//        return user;
//    }


//    @Override
//    public User getUser(String mobileNumber) {
//        User user;
//        try {
//            user = jdbcTemplate.queryForObject("SELECT * from user where mobileNumber = '" + mobileNumber + "' ", new BeanPropertyRowMapper<>(User.class));
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//        return user;
//    }

}
