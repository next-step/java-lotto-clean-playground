package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class WinningLotto {
    private static final int WINNING_CASE_COUNT = 4;
    private static final int THREE_EQUAL_REWARD = 5000;
    private static final int FOUR_EQUAL_REWARD = 50000;
    private static final int FIVE_EQUAL_REWARD = 1500000;
    private static final int SIX_EQUAL_REWARD = 2000000000;


    public HashMap<Integer, Integer> checkEqualCount(ArrayList<ArrayList<Integer>> allLottos, ArrayList<Integer> winningNumbers) {
        HashMap<Integer, Integer> equalCountMap = new HashMap<>();

        for(int i = 0 ; i < WINNING_CASE_COUNT; i++) {
            equalCountMap.put(i + 3, 0);
        }

        for(ArrayList<Integer> singleLotto: allLottos) {
            int singleEqualCount = 0;
            for (Integer number : winningNumbers) {
                if (singleLotto.contains(number)) {
                    singleEqualCount += 1;
                }
            }

            int originalValue = equalCountMap.get(singleEqualCount);
            equalCountMap.put(singleEqualCount, originalValue + 1);
        }

        return equalCountMap;
    }

    // TODO: 조건문 분리 예정 ^^
    public double getLottoProfitRate (HashMap<Integer, Integer> equalCountMap, int purchaseAmount) {
        double totalReward = 0;

        for(int key: equalCountMap.keySet()) {
            if(key == 3) {
                totalReward += equalCountMap.get(key) * THREE_EQUAL_REWARD;
            }
            if(key == 4) {
                totalReward += equalCountMap.get(key) * FOUR_EQUAL_REWARD;
            }
            if(key == 5) {
                totalReward += equalCountMap.get(key) * FIVE_EQUAL_REWARD;
            }
            if(key == 6) {
                totalReward += equalCountMap.get(key) * SIX_EQUAL_REWARD;
            }
        }

        return totalReward / purchaseAmount;
    }
}
