package domain;

import java.util.HashMap;
import java.util.List;

public class WinningLotto {

    public HashMap<MatchResult, Integer> getMatchResult(List<Lotto> allLottos, List<Integer> winningNumbers) {

        HashMap<MatchResult, Integer> resultMap = new HashMap<>();

        for(MatchResult result: MatchResult.values()) {
            resultMap.put(result, 0);
        }

        for(Lotto singleLotto: allLottos) {
            int singleEqualCount = 0;
            for (Integer number : winningNumbers) {
                if (singleLotto.getNumbers().contains(number)) {
                    singleEqualCount += 1;
                }
            }

            for(MatchResult result: MatchResult.values()) {
                if(singleEqualCount == result.getMatchCount()) {
                    resultMap.put(result, resultMap.get(result) + 1);
                }
            }
        }

        return resultMap;
    }

    public double getLottoProfitRate (HashMap<MatchResult, Integer> resultMap, int purchaseAmount) {
        double totalReward = 0;

        for(MatchResult result: MatchResult.values()) {
            totalReward += result.getMatchReward() * resultMap.get(result);
        }

        return totalReward / purchaseAmount;
    }
}
