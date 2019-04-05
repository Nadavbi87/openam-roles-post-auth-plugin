package repositories;

import model.User;

public interface UserRepository {
    User findByUsername(String username);
}
