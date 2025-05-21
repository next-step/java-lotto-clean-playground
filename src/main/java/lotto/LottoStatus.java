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
                .filter(r -> r != MatchResult.NONE)
                .forEach(r -> System.out.printf(
                        "%d개 일치 (%d원)- %d개\n",
                        r.getMatchCount(), r.getPrizeAmount().amount,
                        stat.getOrDefault(r, 0)
                ));
    }

    public Money getTotalPrizeAmount() {
        int sum = 0;
        for (Map.Entry<MatchResult, Integer> entry : stat.entrySet()) {
            sum += entry.getKey().getPrizeAmount().amount * entry.getValue();
        }
        return new Money(sum);
    }


}
