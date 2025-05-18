import java.util.*;

public class LottoResult {
    private final Map<Integer, Integer> result = new HashMap<>();

    public void record(int matchCount) {
        if (!result.containsKey(matchCount)) {
            result.put(matchCount, 0);
        }
        result.put(matchCount, result.get(matchCount) + 1);
    }

    public void print(OutputView output) {
        Map<Integer, Integer> rewards = getRewards();

        for (Integer matchCount : rewards.keySet()) {
            int count = result.getOrDefault(matchCount, 0);
            output.printResultLine(matchCount, rewards.get(matchCount), count);
        }
    }

    public double calculateEarningRate(int purchaseAmount) {
        Map<Integer, Integer> rewards = getRewards();
        long total = 0;

        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int matchCount = entry.getKey();
            int count = entry.getValue();

            // 3개 이상 일치하는 경우에만 당첨금 계산
            if (rewards.containsKey(matchCount)) {
                total += (long) rewards.get(matchCount) * count;
            }
        }

        return (double) total / purchaseAmount;
    }

    private Map<Integer, Integer> getRewards() {
        Map<Integer, Integer> rewards = new HashMap<>();
        rewards.put(3, 5000);
        rewards.put(4, 50000);
        rewards.put(5, 1500000);
        rewards.put(6, 200000000);
        return rewards;
    }
}
