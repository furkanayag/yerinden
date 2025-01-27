package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.UserUpdateRequest;
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

    public EmptyResponse updateUser(UserSession userSession, UserUpdateRequest request){
        User user = findByEmailAndIsActive(userSession.getEmail());
        entityMapper.merge(user, request);
        return new EmptyResponse();
    }

    public User findByEmailAndIsActive(String email) throws BusinessException {
        return repository.findByEmailAndIsActive(email, true).orElseThrow(BusinessException::userNotFound);
    }

    public Optional<User> checkIfUserExist(String email){
        return repository.findByEmailAndIsActive(email, true);
    }

    public EmptyResponse saveUser(User user){
        repository.save(user);
        return new EmptyResponse();
    }
}
