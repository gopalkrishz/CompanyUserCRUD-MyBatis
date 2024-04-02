package com.company.init.mapper;

import com.company.init.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users")
    public List<User> findAllUser();

    @Select("select * from users where username = #{username}")
    public User findUserByName(String username);

    @Select("select * from users where department = #{department}")
    public List<User> findUserByDepartment(String department);

    @Insert("INSERT INTO users (username, email, department, role, salary) VALUES (#{username}, #{email}, #{department}, #{role}, #{salary})")
    public int insert(User user);

    // Update user details by ID
    @Update("UPDATE users SET username=#{username}, email=#{email}, department=#{department}, role=#{role}, salary=#{salary} WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    public int delete(int id);


}
