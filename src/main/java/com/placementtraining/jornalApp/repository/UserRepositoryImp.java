package com.placementtraining.jornalApp.repository;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImp {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUserBySA() {

        String sql = "SELECT * FROM user WHERE email IS NOT NULL ";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    return user;
                });
    }

}