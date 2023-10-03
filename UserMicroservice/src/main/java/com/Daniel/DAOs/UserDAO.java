package com.Daniel.DAOs;

import com.Daniel.Model.User;

public interface UserDAO {
    int findUser(int id);
    int findAll();
    int create(User user);
    int update(User user);
    int delete(int id);

}
