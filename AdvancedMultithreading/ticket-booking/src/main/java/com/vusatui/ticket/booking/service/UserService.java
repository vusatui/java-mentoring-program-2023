package com.vusatui.ticket.booking.service;

import java.util.List;

import com.vusatui.ticket.booking.dao.UserDAO;
import com.vusatui.ticket.booking.model.User;

public class UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public User getUserById(long userId) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return null;
    }

    public User createUser(User user) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public boolean deleteUser(long userId) {
        return false;
    }
}
