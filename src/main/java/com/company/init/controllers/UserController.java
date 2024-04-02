package com.company.init.controllers;
import com.company.init.DTO.UserDTO;
import com.company.init.advice.SqlDuplicationException;
import com.company.init.model.User;
import com.company.init.service.UserHandlingService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users-route")
@Tag(name="CompanyUserControllers",description = "API for company user handling")
public class UserController {
    @Autowired
    private UserHandlingService userHandlingService;

    @GetMapping("/allUsers")
    @Operation(summary = "get all details",description = "api-call to retrieve  all the user details from the company")
    public ResponseEntity<List> getAllUsers(){
        return ResponseEntity.ok(userHandlingService.getAllTheUserDetails());
    }
    @GetMapping("/username/{username}")
    @Operation(summary = "get user details by name",description = "api-call to retrieve  user details by name")
    public ResponseEntity<User> getUserByName(@PathVariable("username") String username){
        return ResponseEntity.ok(userHandlingService.getUserByNameService(username));
    }
    @GetMapping("/department/{department}")
    @Operation(summary = "get user details by department",description = "api-call to retrieve  the user details by department")
    public ResponseEntity<List> getUserByDepartment(@PathVariable("department") String department){
            return ResponseEntity.ok(userHandlingService.getUserByDepartmentService(department));
    }
    @PostMapping("/addUser")
    @Operation(summary = "add User data",description = "api-call to add user data")
    public ResponseEntity<String> addUser(@RequestBody UserDTO inputUser) throws SqlDuplicationException {
        return ResponseEntity.ok(userHandlingService.insertUserData(inputUser));
    }
    @PutMapping("/updateUser/{id}")
    @Operation(summary = "update User data",description = "api-call to update user data")
    public ResponseEntity<Integer> updateUserById(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userHandlingService.updateUserByIdService(id,userDTO));
    }
    @DeleteMapping("/deleteUser/{id}")
    @Hidden
    @Operation(summary = "delete User data",description = "api-call to delete    user data")
    public ResponseEntity<Integer> deleteUserById(@PathVariable("id")int id){
        return ResponseEntity.ok(userHandlingService.deleteUserByIdService(id));
    }
}
