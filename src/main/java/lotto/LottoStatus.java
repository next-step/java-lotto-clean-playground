package lotto;

import java.util.HashMap;
import java.util.Map;

public class LottoStatus {
    private final Map<MatchResult, Integer> stat = new HashMap<>();

    public void record(MatchResult matchResult) {
        stat.put(matchResult, stat.getOrDefault(matchResult, 0) + 1);
    }

    public void print() {
        MatchResult.getAll().stream()
                .map(matchResult -> String.format(
                        "%d개 일치%s (%d원) - %d개",
                        matchResult.getMatchCount(),
                        matchResult.isBonusMatch() ? ", 보너스 볼 일치" : "",
                        matchResult.getPrizeAmount().getAmount(),
                        stat.getOrDefault(matchResult, 0)
                ))
                .forEach(System.out::println);

    }

    public Money getTotalPrizeAmount() {
        int sum = 0;
        for (Map.Entry<MatchResult, Integer> entry : stat.entrySet()) {
            sum += entry.getKey().getPrizeAmount().amount * entry.getValue();
        }
        return new Money(sum);
    }


}
