package com.synchrony.secure.media.api.service;

import com.synchrony.secure.media.api.dto.RegisterRequest;
import com.synchrony.secure.media.api.model.Users;
import com.synchrony.secure.media.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    public Users register(RegisterRequest request) {
        Users user = new Users();
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
        return repo.save(user);

    }

    public Optional<Users> authenticate(String username, String password){
        return repo.findByUsernameAndPassword(username,password);
    }

    public void save(Users user) {
        repo.save(user);
    }

}