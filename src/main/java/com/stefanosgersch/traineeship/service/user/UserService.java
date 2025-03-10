package com.stefanosgersch.traineeship.service.user;

import com.stefanosgersch.traineeship.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void saveUser(User user);
    boolean isUserPresent(User user);
}
