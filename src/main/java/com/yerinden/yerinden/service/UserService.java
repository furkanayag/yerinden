package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.UpdateUserRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.entity.User;
import com.yerinden.yerinden.mapper.EntityMapper;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.UserRepository;
import com.yerinden.yerinden.security.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final EntityMapper entityMapper;

    public EmptyResponse updateUser(UserSession userSession, UpdateUserRequest request){
        User user = findByEmailAndIsActive(userSession.getEmail());

        if (request.getEmail() != null){
            user.setIsEmailVerified(false);
            saveUser(user);
        }

        entityMapper.mergeUser(user, request);
        return new EmptyResponse();
    }

    public User findByEmailAndIsActive(String email) throws BusinessException {
        return repository.findByEmailAndIsActive(email, true).orElseThrow(BusinessException::userNotFound);
    }

    public User findByIdAndIsActive(Long id) throws BusinessException {
        return repository.findByIdAndIsActive(id, true).orElseThrow(BusinessException::userNotFound);
    }

    public Optional<User> checkIfUserExist(String email){
        return repository.findByEmailAndIsActive(email, true);
    }

    public void saveUser(User user){ repository.save(user); }
}
