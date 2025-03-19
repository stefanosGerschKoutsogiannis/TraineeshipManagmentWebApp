package com.stefanosgersch.traineeship.service.auth;

import com.stefanosgersch.traineeship.domain.User;
import com.stefanosgersch.traineeship.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void saveUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public boolean isUserPresent(User user) {
        Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
        return storedUser.isPresent();
    }

    @Override
    public String authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                    String.format("Username %s not found", username)
                ));
    }
}
