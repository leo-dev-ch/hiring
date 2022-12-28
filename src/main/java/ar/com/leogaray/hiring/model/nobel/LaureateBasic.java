package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LaureateBasic {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private Translation name;
    @JsonProperty("knownName")
    private Translation knownName;
    @JsonProperty("fullName")
    private Translation fullName;
    @JsonProperty("portion")
    private PortionEnum portion = PortionEnum._1;
    @JsonProperty("sortOrder")
    private SortOrderEnum sortOrder = SortOrderEnum._1;
    @JsonProperty("motivation")
    private Translation motivation;
    @JsonProperty("links")
    private ItemLinks links;
}

