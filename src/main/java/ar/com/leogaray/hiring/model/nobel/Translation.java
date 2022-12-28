package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Translation {
    @JsonProperty("en")
    private String en;
    @JsonProperty("se")
    private String se;
    @JsonProperty("no")
    private String no;
}