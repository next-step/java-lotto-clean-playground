package dto;

import domain.result.Rank;

import java.util.Map;

public record ResultDto(
        Map<Rank, Integer> results,
        double rateOfReturn
) {
}
