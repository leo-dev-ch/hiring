package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.CategoriaNobel;
import ar.com.leogaray.hiring.model.nobel.NobelPrize;

import java.util.List;


public interface NobelMediaService {
    List<NobelPrize> findNobel(CategoriaNobel categoria, Integer anio);
}
