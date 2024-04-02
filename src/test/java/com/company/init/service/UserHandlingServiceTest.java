package com.company.init.service;

import com.company.init.DTO.UserDTO;
import com.company.init.advice.SqlDuplicationException;
import com.company.init.mapper.UserDTOMapperImplementation;
import com.company.init.mapper.UserMapper;
import com.company.init.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHandlingServiceTest {
    @Autowired
    public UserHandlingService userHandlingService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserDTOMapperImplementation userDTOMapperImplementation;

    @BeforeEach
    void setUp() {
        User user1 =new User(10,"ramesh","ramesh@gmail.com","Backend","dev",50000);
        User user2=new User(11,"suresh","suresh@gmail.com","Backend","full",100000);
        User user3 =new User(12,"vinoth","vinoth@gmail.com","Backend","front-dev",40000);
        User user4 =new User(13,"kamal","kamal@gmail.com","Backend","dev",50000);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        Mockito.when(userMapper.findUserByName("ramesh")).thenReturn(user1);
        Mockito.when(userMapper.findUserByDepartment("Backend")).thenReturn(userList);
        Mockito.when(userMapper.insert(user1)).thenReturn(1);
        Mockito.when(userMapper.update(user1)).thenReturn(1);
        Mockito.when(userMapper.delete(10)).thenReturn(1);

    }
    @Test
    public void getUserAndReturnUserDetailsTest() {
        String username="ramesh";
        User foundUser =userHandlingService.getUserByNameService(username);
        assertEquals(username,foundUser.getUsername());
    }
    @Test
    public void getDepartmentByGivingDepartmentNameTest(){
        String departName="Backend";
        List<User> foundDepartments=userHandlingService.getUserByDepartmentService("Backend");
        assertEquals(departName,foundDepartments.get(0).getDepartment());
    }
    @Test
    public void addUserByGivingUserDetails() throws SqlDuplicationException {
        UserDTO userDto =new UserDTO(10,"ramesh","ramesh@gmail.com","Backend","dev",50000);
        String result = userHandlingService.insertUserData(userDto);
        if(result!=null){
            verify(userMapper,times(1)).insert(Mockito.any(User.class));
        }
        else{
            verify(userMapper,times(0)).insert(Mockito.any(User.class));
        }
    }

    @Test
    public void updateTheUserDetailsByGivingUserId(){
        UserDTO userDto =new UserDTO(10,"ramesh","ramesh@gmail.com","Backend","dev",50000);
        int result =userHandlingService.updateUserByIdService(10,userDto);
        if(result==1){
            verify(userMapper,times(1)).update(Mockito.any(User.class));
        }else{
            verify(userMapper,times(0)).update(Mockito.any(User.class));
        }
    }

    @Test
    public void deleteUserDetailsByGivingUserId(){
        int result = userHandlingService.deleteUserByIdService(10);
        if(result==1){
            verify(userMapper,times(1)).delete(Mockito.any(Integer.class));
        }else{
            verify(userMapper,times(0)).delete(Mockito.any(Integer.class));
        }
    }
}