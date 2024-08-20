package edu.thiago.domain.service;

import edu.thiago.domain.model.User;

public interface UserService {
    User findUserById(Long id);
    User create(User userToCreate);
}
