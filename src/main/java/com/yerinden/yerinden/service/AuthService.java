package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.LoginRequest;
import com.yerinden.yerinden.controller.request.SignUpRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.LoginResponse;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.model.Role;
import com.yerinden.yerinden.security.JwtTokenUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtility tokenUtility;
    private final UserService userService;

    public LoginResponse login(LoginRequest request){
        User user = userService.findByEmailAndIsActive(request.getEmail());
        checkPassword(request.getPassword(), user);
        checkUserRole(user, request.getPlatform());

        log.info("User Login: {}", request.getEmail());

        return new LoginResponse(tokenUtility.generateAccessToken(user));
    }

    //todo add otp auth to sign up flow;
    public EmptyResponse signUp(SignUpRequest request){
        checkIfUserExist(request.getEmail());

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setSurname(request.getSurname());
        newUser.setEmail(request.getEmail());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setRole(Role.BUYER);
        newUser.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        newUser.setIsKKKApproved(true);
        newUser.setIsCommercialsApproved(request.getIsCommercialsApproved());
        newUser.setIsActive(true);
        userService.saveUser(newUser);

        log.info("User Sign Up: {}", request.getEmail());

        return new EmptyResponse();
    }

    private void checkPassword(String pass, User user) throws BusinessException {
        if (!passwordEncoder.matches(pass, user.getPasswordHash())) {throw BusinessException.passwordDoesntMatch();}
    }

    private void checkIfUserExist(String email) throws BusinessException {
        Optional<User> user = userService.checkIfUserExist(email);
        if (user.isPresent()) {throw BusinessException.userAlreadyExist();}
    }

    private void checkUserRole(User user, Role platform) throws BusinessException {
        if (!user.getRole().equals(platform)) {throw BusinessException.incorrectRole();}
    }
}
