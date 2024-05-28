package mario.ferketic.master.service.impl;

import lombok.RequiredArgsConstructor;
import mario.ferketic.master.entity.auth.AuthoritiesEntity;
import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.exceptions.ConflictException;
import mario.ferketic.master.exceptions.ForbiddenException;
import mario.ferketic.master.repository.users.UserRepository;
import mario.ferketic.master.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            throw new ConflictException(String.format("User with username %s already exists", user.getUsername()));
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        var existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isEmpty()) {
            throw new ConflictException(String.format("User with username %s does not exist", user.getUsername()));
        }
        for (AuthoritiesEntity authoritiesEntity : existingUser.get().getAuthoritiesList()) {
            if (authoritiesEntity.getAuthority().equals("ROLE_OWNER") || authoritiesEntity.getAuthority().equals("ROLE_MANAGER")) {
                throw new ForbiddenException("You can't update other Owners or Managers.");
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserAsOwner(User user) {
        var existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isEmpty()) {
            throw new ConflictException(String.format("User with username %s does not exist", user.getUsername()));
        }
        return userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        var user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new ConflictException(String.format("User with username %s does not exist", username));
        }
        return user.get();
    }
}
