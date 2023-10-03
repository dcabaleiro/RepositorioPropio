package com.Daniel.JPA;

import com.Daniel.DAOs.UserDAO;
import com.Daniel.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserJPAService implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int findUser(int id) {

        return 0;
    }

    @Override
    public int findAll() {
        return 0;
    }

    @Override
    public int create(User user) {
        String sql = "INSERT INTO \"BBDDLocal\".\"Users\"(name, surname, login, password) values (?,?,?,?)";
        int result = jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getLogin(), user.getPassword());
        return result;
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE \"BBDDLocal\".\"Users\" SET name=?, surname=?, login=?, password=? where id=?";
        int result = jdbcTemplate.update(sql, user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), user.getId());
        return result;
    }


    @Override
    public int delete(int id) {
        String sql = "DELETE FROM \"BBDDLocal\".\"Users\" where id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result;
    }
}
