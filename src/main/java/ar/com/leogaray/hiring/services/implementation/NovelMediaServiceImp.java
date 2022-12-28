package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.model.CategoriaNobel;
import ar.com.leogaray.hiring.services.NobelMediaService;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.NobelPrizeResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NovelMediaServiceImp implements NobelMediaService {
    private final DefaultApi nobelClient;

    @Override
    public NobelPrizeResult findNobel(CategoriaNobel categoria, Integer anio) throws ApiException {
        String cat = "che";//categoria.getCode();
        NobelPrizeResult list = nobelClient.nobelPrizeCategoryYearGet(cat, anio);

        return list;
    }
}
