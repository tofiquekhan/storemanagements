package com.storesystem.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.storesystem.dto.UserDTO;
import com.storesystem.entity.User;
import com.storesystem.exception.UserNotFoundException;
import com.storesystem.repository.UserRepository;
import com.storesystem.service.UserService;

@Service

public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    

    @Override
    public UserDTO saveUpdateUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
          User savedUser = userRepository.saveAndFlush(user);
          UserDTO savedUserDTO = modelMapper.map(savedUser,UserDTO.class);
        return savedUserDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void removeUserDetailsById(Long id) {
        getUserDetailsById(id);
        userRepository.deleteById(id);
    }
    @Override
    public UserDTO getUserDetailsById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("user not found with id ::"+id);
        }
        User user = userOptional.get();
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

}