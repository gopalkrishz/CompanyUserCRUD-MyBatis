package com.company.init.service;

import com.company.init.DTO.UserDTO;
import com.company.init.advice.SqlDuplicationException;
import com.company.init.advice.UserNotFoundException;
import com.company.init.mapper.UserDTOMapperImplementation;
import com.company.init.mapper.UserMapper;
import com.company.init.validation.UserDataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.company.init.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserHandlingService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public UserDataValidation userDataValidation;

    @Autowired
    public UserDTOMapperImplementation userDTOMapperImplementation;

    public List<User> getAllTheUserDetails(){//get all the user details from the database
        return userMapper.findAllUser();
    }

    public User getUserByNameService(String username){// get the user details by name
        User user = userMapper.findUserByName(username);
        if(user==null){
            throw new UserNotFoundException("The searched user was not found in the database kindly use with another name");
        }
        return user;
    }

    public List<User>  getUserByDepartmentService(String department){//get the user details by the department
        List<User> user = userMapper.findUserByDepartment(department);
        if(user.isEmpty()){
            throw  new UserNotFoundException("the searched department was not found in the database kindly search with another department name");
        }
        return user;
    }
    public int updateUserByIdService(int id,UserDTO userDTO){//update the user details by the user id
        User user=userDTOMapperImplementation.dtoToModel(userDTO);
        user.setId(id);
        int status = userMapper.update(user);
        if (status > 0) {
            return 1;
        } else {
            return 0;
        }
    }
    public int deleteUserByIdService(int id){//delete the user by user id
        int status = userMapper.delete(id);
        if(status>0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public String insertUserData(UserDTO userDTO) throws SqlDuplicationException {//adding the user details in the database by validating some constraints
        if(userDataValidation.checkUsername(userDTO.getUsername())&& userDataValidation.checkEmail(userDTO.getEmail())&& userDataValidation.checkVerifiedDepartment(userDTO.getDepartment())){
            User user = userDTOMapperImplementation.dtoToModel(userDTO);
            try {
                int status = userMapper.insert(user);
                if (status == 1) {
                    return "User data added successfully";
                } else {
                    return "error in storing the data";
                }
                //handling if duplicate username in added
            } catch (DataIntegrityViolationException e) {
//            try {
                throw new SqlDuplicationException("error in storing the data");
//            }
            } catch (Exception e) {
                return "username is invalid retry with another name";
            }
        }else{
            return "The Details you have entered is invalid!!! kindly check it";
        }
    }
}
