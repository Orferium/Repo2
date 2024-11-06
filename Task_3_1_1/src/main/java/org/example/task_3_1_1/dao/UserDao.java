package org.example.task_3_1_1.dao;



import org.example.task_3_1_1.model.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);
}
