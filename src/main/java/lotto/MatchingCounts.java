package lotto;

import java.util.EnumMap;
import java.util.Map;

public class MatchingCounts {
    final Map<LottoResult, Integer> numberCounts = new EnumMap<>(LottoResult.class);

    public void countLottoResult(LottoResult result) {
        numberCounts.put(result, numberCounts.getOrDefault(result, 0) + 1);
    }

    public int getCount(LottoResult result) {
        return numberCounts.getOrDefault(result, 0);
    }

    public int getSumOfReturn() {
        int sum = 0;

        for (Map.Entry<LottoResult, Integer> entry : numberCounts.entrySet()) {
            LottoResult result = entry.getKey();
            int count = entry.getValue();
            sum += result.reward * count;
        }

        return sum;
    }
}
