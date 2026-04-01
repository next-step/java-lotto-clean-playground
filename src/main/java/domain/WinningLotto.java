package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class WinningLotto {
    private static final int WINNING_CASE_COUNT = 4;

    public HashMap<MatchResult, Integer> getMatchResult(ArrayList<ArrayList<Integer>> allLottos, ArrayList<Integer> winningNumbers) {

        HashMap<MatchResult, Integer> resultMap = new HashMap<>();

        for(MatchResult result: MatchResult.values()) {
            resultMap.put(result, 0);
        }

        for(ArrayList<Integer> singleLotto: allLottos) {
            int singleEqualCount = 0;
            for (Integer number : winningNumbers) {
                if (singleLotto.contains(number)) {
                    singleEqualCount += 1;
                }
            }

            for(MatchResult result: MatchResult.values()) {
                if(singleEqualCount == result.getMatchCount()) {
                    int originalCount = resultMap.get(result);
                    resultMap.put(result, originalCount + 1);
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

        // TODO: 수익률 계산 로직 이상함
        return totalReward / purchaseAmount;
    }
}
