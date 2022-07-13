package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.User;
import com.example.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User saveUser(User user) {
		System.out.println("save user");
		System.out.println(user);
		return userRepo.save(user);
	}
	
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
	
    public Optional<User> getUserId(long id) {
        return userRepo.findById(id);
    }
    
    public void deleteUser(long id) {
    	userRepo.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

}
