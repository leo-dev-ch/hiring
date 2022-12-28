package ar.com.leogaray.hiring.model.nobel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NobelPrize {
    @JsonProperty("awardYear")
    private Integer awardYear;
    @JsonProperty("category")
    private Translation category;
    @JsonProperty("categoryFullName")
    private Translation categoryFullName;
    @JsonProperty("dateAwarded")
    private LocalDate dateAwarded;
    @JsonProperty("prizeAmount")
    private Integer prizeAmount;
    @JsonProperty("prizeAmountAdjusted")
    private Integer prizeAmountAdjusted;
    @JsonProperty("topMotivation")
    private Translation topMotivation;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("laureates")
    private List<LaureateBasic> laureates;
    @JsonProperty("meta")
    private Meta meta;
}