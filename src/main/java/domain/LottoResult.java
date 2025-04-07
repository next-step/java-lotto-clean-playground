package domain;

import enums.LottoRank;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> resultByRank;

    private LottoResult(LottoWinningNumbers winningNumbers, Lottos lottos) {
        resultByRank = calculateMatchCount(winningNumbers, lottos);
    }

    public static LottoResult createLottoResult(LottoWinningNumbers winningNumbers, Lottos lottos) {
        validate(lottos);
        return new LottoResult(winningNumbers, lottos);
    }

    public Map<LottoRank, Integer> getResultByRank() {
        return Collections.unmodifiableMap(resultByRank);
    }

    Map<LottoRank, Integer> calculateMatchCount(LottoWinningNumbers winningNumbers, Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();
        Map<LottoRank, Integer> resultByRank = initResultByRank();
        for (Lotto lotto : lottoList) {
            LottoRank lottoRank = LottoRank.determineRank(winningNumbers.matchCount(lotto), winningNumbers.bonusMatch(lotto));
            resultByRank.put(lottoRank, resultByRank.get(lottoRank) + 1);
        }
        return resultByRank;
    }

    private Map<LottoRank, Integer> initResultByRank() {
        Map<LottoRank, Integer> resultByRank = new EnumMap<>(LottoRank.class);
        for (LottoRank lottoRank : LottoRank.values()) {
            resultByRank.put(lottoRank, 0);
        }

        return resultByRank;
    }
}
