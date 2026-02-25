package com.uv.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI uvPowerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UV Power Backend API")
                        .description("API documentation for UV Power Sticker & Laser Engraving Application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("UV Power")
                                .email("support@uvpower.in")
                        )
                );
    }
}
