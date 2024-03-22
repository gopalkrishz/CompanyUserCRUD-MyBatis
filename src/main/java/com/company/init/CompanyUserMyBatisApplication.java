package com.company.init;

import com.company.init.model.User;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MappedTypes(User.class)
@MapperScan("com.company.init.mapper")
@SpringBootApplication
public class CompanyUserMyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyUserMyBatisApplication.class, args);
	}

}
