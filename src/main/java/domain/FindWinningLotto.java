package domain;

import dto.LottoDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindWinningLotto {

    private final static Map<String, Integer> matchCountMap = new HashMap<>();

    public FindWinningLotto() {
        for (int i = 0; i <= 6; i++) {
            matchCountMap.put(String.valueOf(i), 0);
        }
    }

    public LottoDTO countCollectNumber(List<List<Integer>> lottoCollection, List<Integer> lastWeekWinningNumbers, int bonusWinningNumber, int totalInvestment) {
        if (lottoCollection == null || lastWeekWinningNumbers == null || lastWeekWinningNumbers.isEmpty()) {
            return new LottoDTO(0, 0, 0, 0, 0, 0.0D);
        }
        for (List<Integer> lotto : lottoCollection) {
            int matchCount = 0;
            boolean bonusMatch = false;

            for (int number : lotto) {
                if (lastWeekWinningNumbers.contains(number)) {
                    matchCount++;
                }
                if (bonusWinningNumber == number) {
                    bonusMatch = true;
                }
            }
            matchCountMap.put(String.valueOf(matchCount), matchCountMap.get(String.valueOf(matchCount)) + 1);
            if (matchCount == 5 && bonusMatch) {
                matchCountMap.put("5+Bonus", matchCountMap.getOrDefault("5+Bonus", 0) + 1);
            }
        }
        int correct3 = matchCountMap.getOrDefault("3", 0);
        int correct4 = matchCountMap.getOrDefault("4", 0);
        int correct5 = matchCountMap.getOrDefault("5", 0);
        int correct5WithBonus = matchCountMap.getOrDefault("5+Bonus", 0);
        int correct6 = matchCountMap.getOrDefault("6", 0);

        double totalPrizeMoney = (correct3 * 5000)
                + (correct4 * 50000)
                + (correct5 * 1500000)
                + (correct5WithBonus * 30000000)
                + (correct6 * 2000000000);
        double income = totalPrizeMoney / totalInvestment; // 수익률 계산

        return new LottoDTO(correct3, correct4, correct5, correct5WithBonus, correct6, income);
    }
}
