package com.crud.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI productOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product API")
                        .description("API for Managing Products")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("André Matos")
                                .email("andreluisv8@gmail.com")
                                .url("https://github.com/andrematos"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));

    }
}
