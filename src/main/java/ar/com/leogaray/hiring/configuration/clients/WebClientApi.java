package ar.com.leogaray.hiring.configuration.clients;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientApi {
    private final String BASE_URL;

    public WebClientApi(@Value("${ApiNobelPrizeMasterData.baseUrl}") String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    @Bean
    public WebClient getWebClient(WebClient.Builder webClientBuilder) {

        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .baseUrl(BASE_URL)
                .build();
    }

    private HttpClient getHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10)));
    }
}
