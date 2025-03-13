package com.stefanosgersch.traineeship.service.user;

import com.stefanosgersch.traineeship.domain.User;
import com.stefanosgersch.traineeship.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(User user) {
        user.setPassword("password");
        userRepository.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
        return storedUser.isPresent();
    }
}
