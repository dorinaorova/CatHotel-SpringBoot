package com.cathotel.cathotel.service;


import com.cathotel.cathotel.model.User;
import com.cathotel.cathotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository userRepo){
        repository=userRepo;
    }

    public User addUser(User newUser){
        return repository.save(newUser);
    }

    public List<User> findAllUser(){
        return repository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return repository.findById(id);
    }

    public User updateUser(User user){
        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }


    public User findUserByEmail(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.get();
    }

}
