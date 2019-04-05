package services.impl;

import model.User;
import repositories.UserRepository;
import services.UserService;

public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


}
