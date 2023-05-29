package com.storesystem.service;

import java.util.List;

import com.storesystem.dto.UserDTO;

public interface UserService{
	UserDTO saveUpdateUser(UserDTO userDTO);
	List<UserDTO> getUsers();
	void removeUserDetailsById(Long id);
	UserDTO getUserDetailsById(Long id);
	
}