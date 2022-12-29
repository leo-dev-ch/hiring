package ar.com.leogaray.hiring.configuration.swagger;

import com.google.api.client.util.DateTime;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

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

    private static ApiResponses getDefaultResponseMessages() {

        ApiResponses apiResponses = new ApiResponses();
        apiResponses
                .addApiResponse("400", createExceptionResponse("Invalid Request (e.g. not nullable field is null)"));
        apiResponses.addApiResponse("401", createExceptionResponse("Unauthorized"));
        apiResponses.addApiResponse("403", createExceptionResponse("Forbidden"));
        apiResponses.addApiResponse("404", createExceptionResponse("Job Posting Not Found"));
        apiResponses
                .addApiResponse("410", createExceptionResponse("Job Posting No Longer Available (i.e. deleted)"));
        apiResponses
                .addApiResponse("422", createExceptionResponse("Entity Referenced by ID in the Body was Not Found"));
        apiResponses.addApiResponse("500", createExceptionResponse("Failure"));

        return apiResponses;
    }

    private static ApiResponse createExceptionResponse(String message) {
        Schema exceptionResponseSchema = new Schema();
        exceptionResponseSchema.setName("ExceptionResponse");
        exceptionResponseSchema.set$ref("#/components/schemas/ExceptionResponse");

        MediaType mediaType = new MediaType();
        mediaType.schema(exceptionResponseSchema);
        return new ApiResponse().description(message)
                .content(new Content().addMediaType(
                        org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType));
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("bearerAuth")).components(new Components().addSecuritySchemes("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).info(getInfo());
    }

    private Info getInfo() {
        return new Info().title(this.title).description(this.description).termsOfService(this.termsOfServiceUrl).contact(new Contact().name(this.contact)).license(new License().name(this.licenseName).url(this.licenseUrl)).version(this.version);
    }

    @Bean
    public OperationCustomizer customResponses() {
        ApiResponses defaultApiResponses = getDefaultResponseMessages();
        return (Operation operation, HandlerMethod handlerMethod) -> {
            operation.getResponses().putAll(defaultApiResponses);
            return operation;
        };
    }

}
