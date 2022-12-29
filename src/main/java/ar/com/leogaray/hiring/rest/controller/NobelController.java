package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.model.nobel.NobelPrize;
import ar.com.leogaray.hiring.rest.model.CategoriaNobel;
import ar.com.leogaray.hiring.services.NobelMediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nobel")
@AllArgsConstructor
@Tag(name = "Nobel")
public class NobelController {
    private final NobelMediaService nobelService;

    @GetMapping(value = "/{categoria}/{anio}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "busca nobel")
    public List<NobelPrize> get(@PathVariable CategoriaNobel categoria,
                                @PathVariable Integer anio) {

        return nobelService.findNobel(ar.com.leogaray.hiring.model.CategoriaNobel.getCategoriaByCode(categoria.getCode()), anio);
    }
}
