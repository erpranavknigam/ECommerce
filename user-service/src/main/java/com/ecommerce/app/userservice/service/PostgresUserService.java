package com.ecommerce.app.userservice.service;

import com.ecommerce.app.userservice.model.postgres.PostgresUser;
import com.ecommerce.app.userservice.repository.postgres.PostgresUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostgresUserService {
    private final PostgresUserRepository userRepository;

    @Autowired
    public PostgresUserService(PostgresUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PostgresUser createUser(PostgresUser user){
        if(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("User with same email or username already exists");
        }
        return userRepository.save(user);
    }

    public List<PostgresUser> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<PostgresUser> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("User with the give ID does not exists");
        }
        userRepository.deleteById(id);
    }

    public PostgresUser updateUser(Long id, PostgresUser updatedUser){
        return userRepository.findById(id)
                .map(x->{
                    x.setUsername(updatedUser.getUsername());
                    x.setEmail(updatedUser.getEmail());
                    x.setPassword(updatedUser.getPassword());
                    return userRepository.save(x);
                })
                .orElseThrow(() -> new IllegalArgumentException("User with given ID not found."));
    }

}
