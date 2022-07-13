package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Service.UserService;
import com.example.controller.UserController;
import com.example.entity.User;
import com.example.entity.User.UserBuilder;
import com.example.repository.UserRepo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepo userRepo;
	
	@InjectMocks
	private UserService userService;
	
	private User user;
	
    @BeforeEach
    public void setup(){
    	user  = User.builder().id(1).name("Kushang").email("Kushang@mail.com").build();
      }
	
	@DisplayName("Test case for save user method")
	@Test
	void saveUserTest() {

//		given(userRepo.findById(user.getId())).willReturn(Optional.empty());
		
		given(userRepo.save(user)).willReturn(user);
		
		User savedUser = userService.saveUser(user);
		
		assertThat(savedUser).isNotNull();
		
	}

	@DisplayName("Test case to get all users")
	@Test
	void getAllUsers() {

		User user2 = User.builder().id(2L).name("Kushnag1234").email("MyMail@kushang.com").build();
		
		given(userRepo.findAll()).willReturn(List.of(user, user2));
		
		List<User> userList = userService.getAllUsers();
		
		assertThat(userList).isNotNull();
		assertThat(userList.size()).isEqualTo(2);
		
	}
	
	@DisplayName("Test case to get user by ID")
	@Test
	void getUsersByIdTest() {

		
		given(userRepo.findById(1L)).willReturn(Optional.of(user));
		
		User savedUser = userService.getUserId(1).get();
		
		assertThat(savedUser).isNotNull();
		
	}
	
	@DisplayName("Test case to Update User")
	@Test
	void updateUserTest() {

		
		given(userRepo.save(user)).willReturn(user);
		user.setEmail("MyMail@kushang.com");
		user.setName("Kushang_Suratwala");
		
		
		User updatedUser = userService.updateUser(user);
		
		assertThat(updatedUser.getName()).isEqualTo("Kushang_Suratwala");
		assertThat(updatedUser.getEmail()).isEqualTo("MyMail@kushang.com");
		
	}
	
	@DisplayName("Test case to Delete User")
	@Test
	void deleteUserTest() {

		long userId = 1L;

		userService.deleteUser(userId);
		
		verify(userRepo, times(1)).deleteById(userId);
		
	}
	
	
	@DisplayName("Test case to get by id negative  User")
	@Test
	void getUserByIdNotFound() {
		
		long userId = 7L;
		
		Boolean r = userRepo.existsById(userId);
		
		assertThat(r).isFalse();
	}
	
	
	
}
