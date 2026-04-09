package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WinningLotto {

    private static final int BONUS_BALL_VALID_EQUAL_COUNT_THRESHOLD = 5;

    public HashMap<MatchResult, Integer> getMatchResult(List<Lotto> allLottos, List<LottoNumber> winningNumbers, LottoNumber bonusBall) {
        HashMap<MatchResult, Integer> resultMap = new HashMap<>();

        for (Lotto singleLotto : allLottos) {
            int singleEqualCount = singleLotto.getWinningNumberMatchCount(winningNumbers);
            boolean isBonusBallMatch = singleLotto.isBonusBallMatch(bonusBall);

            MatchResult result = MatchResult.getResultByMatchCount(singleEqualCount, isBonusBallMatch);

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
