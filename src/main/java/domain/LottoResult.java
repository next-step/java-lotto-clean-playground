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
        Prize prize = calculatePrize(lotto, winningNumbers, bonusNumber);

        if (prize != Prize.NONE) {
            matchCountMap.put(prize, matchCountMap.getOrDefault(prize, 0) + 1);
        }
    }

    private Prize calculatePrize(Lotto lotto, List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        int matchCount = lotto.calculateMatchCount(winningNumbers);
        boolean isBonusMatched = lotto.numbers().contains(bonusNumber);
        return Prize.of(matchCount, isBonusMatched);
    }

    public static LottoResult from(List<Lotto> lottoList, WinningLotto winningLotto) {
        return new LottoResult(lottoList, winningLotto.getNumbers(), winningLotto.getBonusNumber());
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
