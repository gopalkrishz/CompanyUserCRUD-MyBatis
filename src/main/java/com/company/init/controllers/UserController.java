package com.company.init.controllers;

import com.company.init.DTO.UserDTO;
import com.company.init.advice.SqlDuplicationException;
import com.company.init.model.User;
import com.company.init.service.UserHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-route")
public class UserController {
    @Autowired
    private UserHandlingService userHandlingService;

    @GetMapping("/allUsers")
    public ResponseEntity<List> getAllUsers(){
        return ResponseEntity.ok(userHandlingService.getAllTheUserDetails());
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<List> getUserByName(@PathVariable("username") String username){
        return ResponseEntity.ok(userHandlingService.getUserByNameService(username));
    }
    @GetMapping("/department/{department}")
    public ResponseEntity<List> getUserByDepartment(@PathVariable("department") String department){
            return ResponseEntity.ok(userHandlingService.getUserByDepartmentService(department));
    }
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) throws SqlDuplicationException {
        return userHandlingService.insertUserData(userDTO);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userHandlingService.updateUserByIdService(id,userDTO));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id")int id){
        return ResponseEntity.ok(userHandlingService.deleteUserByIdService(id));
    }
}
