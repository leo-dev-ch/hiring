package ar.com.leogaray.hiring.configuration.clients;

import io.swagger.client.ApiClient;
import io.swagger.client.api.DefaultApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NobelMediaClient {
    private final ApiClient apiClient = new ApiClient();

    @Bean(name = "DefaultApi")
    public DefaultApi apiController() {
        return new DefaultApi(apiClient);
    }
}
