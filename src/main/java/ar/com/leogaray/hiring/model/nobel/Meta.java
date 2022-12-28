package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Meta {
    @JsonProperty("terms")
    private String terms;
    @JsonProperty("license")
    private String license;
    @JsonProperty("disclaimer")
    private String disclaimer;
}
