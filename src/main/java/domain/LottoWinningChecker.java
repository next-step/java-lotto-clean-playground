package domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoWinningChecker {
    private LottoWinningChecker() {
    }

    public static LottoResult checkLotto(Lottos lottos, WinningNumbers winningNumbers) {
        Map<Prize, Long> counts = lottos.getLottos().stream()
                .map(lotto -> Prize.fromMatchCount(countMatch(lotto, winningNumbers)))
                .filter(Prize::isWinning)
                .collect(Collectors.groupingBy(
                        prize -> prize,
                        () -> new EnumMap<>(Prize.class),
                        Collectors.counting()
                ));

        return buildResult(counts);
    }

    private static int countMatch(Lotto lotto, WinningNumbers winningNumbers) {
        return (int) lotto.getNumbers().stream()
                .filter(winningNumbers.getWinningNumbers().getNumbers()::contains)
                .count();
    }

    private static LottoResult buildResult(Map<Prize, Long> counts) {
        return new LottoResult(
                counts.getOrDefault(Prize.FIRST,  0L).intValue(),
                counts.getOrDefault(Prize.SECOND, 0L).intValue(),
                counts.getOrDefault(Prize.THIRD,  0L).intValue(),
                counts.getOrDefault(Prize.FOURTH, 0L).intValue()
        );
    }
}

