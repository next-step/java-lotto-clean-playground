package dto;

import domain.Rank;

import java.util.Map;

public record ResultDto(
        Map<Rank, Integer> results,
        double rateOfReturn
) {
}
