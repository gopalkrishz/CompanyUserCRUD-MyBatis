package com.company.init;

import com.company.init.model.User;
import jdk.javadoc.doclet.Doclet;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@MappedTypes(User.class)
@MapperScan("com.company.init.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.company.init", "com.company.init.config"}) // Include package containing SwaggerConfig
public class CompanyUserMyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyUserMyBatisApplication.class, args);

	}





}
