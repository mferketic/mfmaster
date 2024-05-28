package mario.ferketic.master.service.security;

import mario.ferketic.master.entity.users.User;
import mario.ferketic.master.repository.users.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService, Serializable {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        user.orElseThrow(() -> new UsernameNotFoundException("user " + username + " ne postoji!"));
        return new MyUserDetails(user.get().getUsername(), user.get().getPassword(), user.get().getFirstname(), user.get().getLastname(), user.get().getAuthoritiesList());
    }
}
