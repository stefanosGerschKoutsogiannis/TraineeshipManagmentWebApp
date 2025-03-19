package com.stefanosgersch.traineeship.service.auth;

import com.stefanosgersch.traineeship.domain.User;

public interface AuthService {

    void saveUser(User user);
    boolean isUserPresent(User user);
    String authenticateUser();
}
