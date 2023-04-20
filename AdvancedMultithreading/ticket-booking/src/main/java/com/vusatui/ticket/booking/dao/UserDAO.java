package com.vusatui.ticket.booking.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

import com.vusatui.ticket.booking.model.User;
import com.vusatui.ticket.booking.model.UserDTO;

public class UserDAO {

    private static final Map<Long, User> users = new HashMap<>();

    private static final RandomGenerator randomGenerator = new Random();

    public long createUser(String name, String email) {
        User user = new UserDTO(randomGenerator.nextLong(), name, email);
        users.put(user.getId(), user);
        return user.getId();
    }

    public long updateUser(User updatedUser) {
        users.put(updatedUser.getId(), updatedUser);
        return updatedUser.getId();
    }

    public User getUser(long userId) {
        return users.get(userId);
    }

    public void deleteUser(long userId) {
        users.remove(userId);
    }

}
