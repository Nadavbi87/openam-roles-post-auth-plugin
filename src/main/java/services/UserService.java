package services;

import model.User;

public interface UserService {
    User findByUsername(String username);
}
