package com.new_year_classmates.Services;

import com.new_year_classmates.Models.User;
import com.new_year_classmates.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).get();
    }
}
