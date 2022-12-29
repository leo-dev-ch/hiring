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
import org.springframework.web.bind.annotation.DeleteMapping;
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


    private static ApiResponses getDeleteResponseMessages() {

        ApiResponses apiResponses = new ApiResponses();
        apiResponses.addApiResponse("204", createEmptyResponse("Delete successfully"));

        return apiResponses;
    }

    private static ApiResponse createExceptionResponse(String message) {
        Schema exceptionResponseSchema = new Schema();
        exceptionResponseSchema.setName("ExceptionResponse");
        exceptionResponseSchema.set$ref("#/components/schemas/ExceptionResponse");

        MediaType mediaType = new MediaType();
        mediaType.schema(exceptionResponseSchema);
        return new ApiResponse().description(message).content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType));
    }

    private static ApiResponse createEmptyResponse(String message) {
        return new ApiResponse().description(message);
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
        // ApiResponses defaultApiResponses = getDefaultResponseMessages();
        ApiResponses deleteApiReponses = getDeleteResponseMessages();

        return (Operation operation, HandlerMethod handlerMethod) -> {
            //operation.getResponses().putAll(defaultApiResponses);
            if (handlerMethod.hasMethodAnnotation(DeleteMapping.class)) {
                operation.getResponses().putAll(deleteApiReponses);
            }
            return operation;
        };
    }


}
