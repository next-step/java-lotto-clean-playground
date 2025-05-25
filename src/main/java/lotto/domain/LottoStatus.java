package lotto.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LottoStatus {
    private final Map<MatchResult, Integer> stat = new HashMap<>();

    public void record(MatchResult matchResult) {
        stat.put(matchResult, stat.getOrDefault(matchResult, 0) + 1);
    }

    public void print(int ticketCount, int minimumAmount) {
        System.out.println("당첨 통계\n--------");
        MatchResult.getAll().stream()
                .filter(matchResult -> matchResult != MatchResult.NONE)
                .sorted(Comparator.comparingInt(MatchResult::getMatchCount))
                .map(matchResult -> {
                    String matchLabel = matchResult.getMatchCount() + "개 일치";
                    if (matchResult.isBonusMatch()) {
                        matchLabel += ", 보너스 볼 일치";
                    }
                    return String.format(
                            "%s (%d원)- %d개",
                            matchLabel,
                            matchResult.getPrizeAmount().getAmount(),
                            stat.getOrDefault(matchResult, 0)
                    );
                })
                .forEach(System.out::println);
        double returnRate = (double) getTotalPrizeAmount().getAmount()
                / (ticketCount * minimumAmount);
        System.out.printf("총 수익률은 %.2f 입니다.%n", returnRate);

    }

    public Money getTotalPrizeAmount() {
        int sum = 0;
        for (Map.Entry<MatchResult, Integer> entry : stat.entrySet()) {
            sum += entry.getKey().getPrizeAmount().amount * entry.getValue();
        }
        return new Money(sum);
    }


}
