package mario.ferketic.master.service;

import mario.ferketic.master.entity.users.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    User updateUserAsOwner(User user);

    User getByUsername(String username);
}
