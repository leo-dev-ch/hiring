package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.common.exceptions.ApiClientException;
import ar.com.leogaray.hiring.model.CategoriaNobel;
import ar.com.leogaray.hiring.model.nobel.NobelPrize;
import ar.com.leogaray.hiring.services.NobelMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.util.List;

@Component

public class NovelMediaServiceImp implements NobelMediaService {
    private static final Logger logger = LoggerFactory.getLogger(NovelMediaServiceImp.class);
    @Resource
    private WebClient webClient;
    private final String PATH_NOBEL_PRIZE;

    public NovelMediaServiceImp(WebClient webClient, @Value("${ApiNobelPrizeMasterData.nobelPrize}") String PATH_NOBEL_PRIZE) {
        this.webClient = webClient;
        this.PATH_NOBEL_PRIZE = PATH_NOBEL_PRIZE;
    }

    @Override
    public List<NobelPrize> findNobel(CategoriaNobel categoria, Integer anio) {

        return webClient.get()
                .uri(PATH_NOBEL_PRIZE, categoria.getCode(), anio)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> {
                    logger.error("Error endpoint with status code {}", clientResponse.statusCode());
                    throw new ApiClientException("HTTP Status error");
                })
                .bodyToMono(new ParameterizedTypeReference<List<NobelPrize>>() {
                })
                .block();
    }
}
