package service.converter;

import domain.LottoRank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultConverter {
    public static List<List<Integer>> mapToList(Map<LottoRank, Integer> rankResults) {
        return rankResults.entrySet().stream()
                .map(entry -> List.of(
                        entry.getKey().getMatchCount(),
                        entry.getKey().getHasValue(),
                        entry.getKey().getRank(),
                        entry.getKey().getPrizeMoney(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }
}
