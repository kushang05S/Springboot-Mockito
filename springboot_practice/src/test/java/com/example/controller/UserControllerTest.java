package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Service.UserService;
import com.example.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	@Mock
	private UserService userService;

	@InjectMocks	
	private UserController userController;
	
	private User user;
	
	@BeforeEach
	public void setup() {
		user = User.builder().id(1L).name("Kushang").email("Ksuhang@mail.com").build();
	}
	
	@DisplayName("Test case for save user method")
	@Test
	void saveUserTest() {

		given(userService.saveUser(user)).willReturn(user);
		
		User savedUser = userController.newUser(user);
		
		assertThat(savedUser).isNotNull();
		
	}

	@DisplayName("Test case to get all users")
	@Test
	void getAllUsers() {

		User user2 = User.builder().id(2L).name("Kushnag1234").email("MyMail@kushang.com").build();
		
		given(userService.getAllUsers()).willReturn(List.of(user, user2));
		
		List<User> userList = userController.getAllUser();
		
		assertThat(userList).isNotNull();
		assertThat(userList.size()).isEqualTo(2);
		
	}
	
	@DisplayName("Test case to get user by ID")
	@Test
	void getUsersByIdTest() {

		
		given(userService.getUserId(1L)).willReturn(Optional.of(user));
		
		User savedUser = userController.getUser(1L).get();
		
		assertThat(savedUser).isNotNull();
		
	}
	
	@DisplayName("Test case to Update User")
	@Test
	void updateUserTest() {

		
		given(userService.updateUser(user)).willReturn(user);
		user.setEmail("MyMail@kushang.com");
		user.setName("Kushang_Suratwala");
		
		
		User updatedUser = userController.updateUser(user);
		
		assertThat(updatedUser.getName()).isEqualTo("Kushang_Suratwala");
		assertThat(updatedUser.getEmail()).isEqualTo("MyMail@kushang.com");
		
	}
	
	@DisplayName("Test case to Delete User")
	@Test
	void deleteUserTest() {

		long userId = 1L;
		
		userController.deleteUser(userId);;
		verify(userService, times(1)).deleteUser(userId);;
		
	}
	
	@DisplayName("Test case to get user by id not found")
	@Test
	void getUserByIdTestNotFound() {

		long userId = 2L;
		
		boolean getUser = userService.getUserId(userId).isPresent();
		
		assertThat(getUser).isFalse();
		
	}
	
	
	
}

