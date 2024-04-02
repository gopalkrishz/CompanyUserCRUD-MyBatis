package com.company.init.controllers;

import com.company.init.DTO.UserDTO;
import com.company.init.mapper.UserDTOMapperImplementation;
import com.company.init.mapper.UserMapper;
import com.company.init.model.User;
import com.company.init.service.UserHandlingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
@WebMvcTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserHandlingService userHandlingService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserDTOMapperImplementation userDTOMapperImplementation;
    public User user1;
    public User user2;

    public UserDTO inputUser;
    public UserDTO inputUser1;
    @BeforeEach
    void setUp() {
        user1 =new User(10,"ramesh","ramesh@gmail.com","Backend","dev",50000);
        user2 =new User(11,"rakul","rakul@gmail.com","Backend","senior-dev",150000);
        inputUser = new UserDTO(10, "ramesh", "ramesh@gmail.com", "Backend", "dev", 50000);
        inputUser1 = new UserDTO(10, "ramesh", "ramesh@gmail.com", "Backend", "dev", 50000);
    }

    @Test
    void addUserDetailsInTheDatabaseTest() throws Exception {
      Mockito.when(userHandlingService.insertUserData(Mockito.any(UserDTO.class))).thenReturn("User data added successfully");

        mockMvc.perform(MockMvcRequestBuilders.post("/users-route/addUser").contentType(MediaType.APPLICATION_JSON)
                .content("\t{\n" +
                        "\t\t\"id\":10,\n" +
                        "\t\t\"username\": \"ramesh\",\n" +
                        "\t\t\"email\": \"ramesh@exampggle.com\",\n" +
                        "\t\t\"department\": \"Backend\",\n" +
                        "\t\t\"role\": \"dev\",\n" +
                        "\t\t\"salary\":50000\n" +
                        "\t}")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        verify(userHandlingService, times(1)).insertUserData(Mockito.any(UserDTO.class));
    }
    @Test
    void fetchUserDetailsByGivingDepartmentName() throws Exception {
        Mockito.when(userHandlingService.getUserByNameService("ramesh")).thenReturn(user1);
        String responseJson=mockMvc.perform(MockMvcRequestBuilders.get("/users-route/username/ramesh").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();//json

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(responseJson,User.class);
        assertEquals(user.getUsername(),"ramesh");
    }
    @Test
    void fetchUserDetailsByGivingTheDepartmentNameTest() throws Exception {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        Mockito.when(userHandlingService.getUserByDepartmentService("Backend")).thenReturn(userList);
        String response =mockMvc.perform(MockMvcRequestBuilders.get("/users-route/department/Backend").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        String department=null;
        for(JsonNode j :jsonNode){
            department = j.get("department").asText();
        }
        assertEquals(department,"Backend");
    }
    @Test
    void updateUserByIdAndReceiveUpdatedValue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users-route/updateUser/22")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"username\": \"gopal\",\n" +
                        "\t\"email\": \"gopal@example.com\",\n" +
                        "\t\"department\": \"IT\",\n" +
                        "\t\"role\": \"IT\",\n" +
                        "\t\"salary\": 70000\n" +
                        "}"))
                .andReturn();

        verify(userHandlingService,times(1)).updateUserByIdService(Mockito.any(Integer.class),Mockito.any(UserDTO.class));
    }
    @Test
    void deleteUserDetailsIfUserIdGiven() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users-route/deleteUser/22")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        verify(userHandlingService,times(1)).deleteUserByIdService(Mockito.any(Integer.class));
    }
}