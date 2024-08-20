package edu.thiago.domain.service.impl;

import edu.thiago.domain.model.User;
import edu.thiago.domain.repository.UserRepository;
import edu.thiago.domain.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userToCreate.getId() != null || userRepository.existsById(userToCreate.getId())
                || userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new DuplicateKeyException("This Account already exists");
        }

        return userRepository.save(userToCreate);
    }
}
