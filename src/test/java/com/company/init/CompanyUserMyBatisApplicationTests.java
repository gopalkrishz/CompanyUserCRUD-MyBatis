package com.company.init;

import com.company.init.DTO.UserDTO;
import com.company.init.mapper.UserMapper;
import com.company.init.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment  =SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyUserMyBatisApplicationTests {

	@LocalServerPort
	public int port;

	private static RestTemplate restTemplate;

	public String baseUrl ="http://localhost";

	@BeforeAll
	public static  void init(){
		restTemplate= new RestTemplate();
	}

	@Autowired
	private UserMapper userMapper;
	@BeforeEach
	public void setUp(){
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/users-route");
	}

	@Test
	public void testGetUserByName(){
		String username="gopal";
		User user =restTemplate.getForObject(baseUrl+"/username/"+username,User.class);
		assertEquals(username,user.getUsername());
	}

	@Test
	public void testGetDepartmentByGivingDepartmentName(){
		String department="it";
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
				baseUrl + "/department/" + department,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>() {});

		List<User> userList = responseEntity.getBody();
		User sampleUser = userList.get(0); // Assuming you want to print the first user
		assertEquals(department,sampleUser.getDepartment().toLowerCase());
	}
	@Test
	public void testUserByAddingTheUserDetails() throws Exception{
		UserDTO user1 =new UserDTO(19,"ramarar","ramarar@gmail.com","backend","senior-dev",150000);
		String response = restTemplate.postForObject(baseUrl+"/addUser",user1,String.class);
		System.out.println(response);
		if(response!=null){
			assertEquals("backend",userMapper.findUserByName("ramarar").getDepartment());
		}
	}


}

