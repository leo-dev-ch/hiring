package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.CategoriaNobel;
import io.swagger.client.ApiException;
import io.swagger.client.model.NobelPrizeResult;


public interface NobelMediaService {
    NobelPrizeResult findNobel(CategoriaNobel categoria, Integer anio) throws ApiException;
}
