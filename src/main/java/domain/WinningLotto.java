package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WinningLotto {

    public HashMap<MatchResult, Integer> getMatchResult(List<Lotto> allLottos, List<LottoNumber> winningNumbers) {
        HashMap<MatchResult, Integer> resultMap = new HashMap<>();

        for (Lotto singleLotto : allLottos) {
            int singleEqualCount = singleLotto.getWinningNumberMatchCount(winningNumbers);

            MatchResult result = MatchResult.getResultByMatchCount(singleEqualCount);

            if (result != MatchResult.MISS) {
                resultMap.put(result, resultMap.getOrDefault(result, 0) + 1);
            }
        }

        return resultMap;
    }

    public double getLottoProfitRate(HashMap<MatchResult, Integer> resultMap, int purchaseAmount) {
        double totalReward = 0;

        for (MatchResult result : MatchResult.values()) {
            totalReward += result.getMatchReward() * resultMap.getOrDefault(result, 0);
        }

        return totalReward / purchaseAmount;
    }
}
