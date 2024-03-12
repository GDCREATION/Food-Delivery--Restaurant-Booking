package com.nagp.userservice.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<Long, User> userMap = new HashMap<>();
    private final List<User> userList = new ArrayList<>();
    private Long currentId = 1L;

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public User save(User user) {
        if (user.getId() == null) {
            // If the user does not have an ID, assign a new one
            user.setId(currentId);
            currentId++;
        }
        userMap.put(user.getId(), user);
        userList.add(user);
        return user;
    }

    public List<User> findAll() {
        return new ArrayList<>(userList);
    }

    public void deleteById(Long id) {
        User user = userMap.remove(id);
        if (user != null) {
            userList.remove(user);
        }
    }
}
