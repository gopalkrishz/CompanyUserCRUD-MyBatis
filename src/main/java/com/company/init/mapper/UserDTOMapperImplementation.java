package com.company.init.mapper;

import com.company.init.DTO.UserDTO;
import com.company.init.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDTOMapperImplementation{

    public UserDTO modelToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setDepartment(user.getDepartment());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setSalary(user.getSalary());
        return userDTO;
    }


    public User dtoToModel(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setDepartment(userDTO.getDepartment());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        user.setSalary(userDTO.getSalary());
        return user;
    }
}
