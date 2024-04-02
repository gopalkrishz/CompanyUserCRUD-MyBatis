package com.company.init.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("User Details API")
                .description("The REST CRUD API to store all the user data from the company database")
                .version("1.0")
                .contact(new Contact().name("gopal").email("gunagopal931@gmail.com"))
                .license(new License().name("Apache2"))
        ).externalDocs(new ExternalDocumentation().description("REST API for userDB"));
    }
}
