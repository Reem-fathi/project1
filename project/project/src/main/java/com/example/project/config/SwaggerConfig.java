package com.example.project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "Employee details managing system",
    description = "",
    version = "1.0",
//                termsOfServiceUrl = "https://example.com/tos",
    contact = @Contact(
      name = "Reema Fathima",
      email = "reemafatma@gmail.com",
      url = "https://Reem-fathi.tk"
    ),
    license = @License(
      name = "Apache 2.0",
      url = "https://www.apache.org/licenses/LICENSE-2.0.html"
    )
  ),
  servers = @Server(
    url = "http://localhost:8080/",
    description = "Production Server"
  ),
  security = @SecurityRequirement(name = "bearerAuth")
)
public class SwaggerConfig {

}
