package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Links {
    @JsonProperty("rel")
    private String rel;
    @JsonProperty("href")
    private String href;
    @JsonProperty("action")
    private String action;
    @JsonProperty("types")
    private String types;
}
