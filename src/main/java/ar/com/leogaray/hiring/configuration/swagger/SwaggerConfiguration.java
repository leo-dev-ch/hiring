package ar.com.leogaray.hiring.configuration.swagger;

import com.google.api.client.util.DateTime;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "springdoc.swagger-ui")
public class SwaggerConfiguration {

    private String title = "";
    private String description = "";
    private String termsOfServiceUrl = "";
    private String contact = "";
    private String licenseName = "";
    private String licenseUrl = "";
    private String version = "";
    private String apiGroupName = "";

    public SwaggerConfiguration() {
        SpringDocUtils.getConfig().replaceWithClass(DateTime.class, Date.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("bearerAuth")).components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(getInfo());
    }

    private Info getInfo() {
        return new Info().title(this.title).description(this.description).termsOfService(this.termsOfServiceUrl).contact(new Contact().name(this.contact)).license(new License().name(this.licenseName).url(this.licenseUrl)).version(this.version);
    }
 }
