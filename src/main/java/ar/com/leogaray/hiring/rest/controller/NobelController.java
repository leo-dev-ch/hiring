package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.rest.model.CategoriaNobel;
import ar.com.leogaray.hiring.services.NobelMediaService;
import io.swagger.client.ApiException;
import io.swagger.client.model.NobelPrizeResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nobel")
@AllArgsConstructor
@Tag(name = "Nobel")
public class NobelController {
    private final NobelMediaService nobelService;

    @GetMapping(value = "/{categoria}/{anio}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "-")
    public NobelPrizeResult get(@PathVariable CategoriaNobel categoria,
                                @PathVariable Integer anio) throws ApiException {


        return nobelService.findNobel(ar.com.leogaray.hiring.model.CategoriaNobel.getCategoriaByCode(categoria.getCode()), anio);
    }
}
