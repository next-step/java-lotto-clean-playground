package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Prize, Integer> matchCountMap = new HashMap<>();

    private LottoResult(List<Lotto> lottoList, List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        for (Lotto lotto : lottoList) {
            recordLottoResult(lotto, winningNumbers, bonusNumber);
        }
    }

    private void recordLottoResult(Lotto lotto, List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int matchCount = calculateMatchCount(lotto, winningNumbers);
        if (matchCount < 3) return;

        boolean isBonusMatched = lotto.numbers().contains(bonusNumber);
        Prize prize = Prize.of(matchCount, isBonusMatched);

        if (prize == Prize.NONE) {
            prize = Prize.of(matchCount, false);
        }

        matchCountMap.put(prize, matchCountMap.getOrDefault(prize, 0) + 1);
    }

    public int calculateMatchCount(Lotto lotto, List<LottoNumber> winningNumbers) {
        return (int) lotto.numbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public static LottoResult from(List<Lotto> lottoList, WinningLotto winningLotto) {
        return new LottoResult(lottoList, winningLotto.getWinningNumbers(), winningLotto.getBonusNumber());
    }

    public long calculateTotalPrize() {
        return matchCountMap.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }

    public Map<Prize, Integer> getMatchCountMap() {
        return Collections.unmodifiableMap(matchCountMap);
    }
}
