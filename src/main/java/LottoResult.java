import java.util.*;

public class LottoResult {
    private final Map<Rank, Integer> result = new HashMap<>();

    public void record(int matchCount, boolean bonusMatch) {
        Rank rank = Rank.valueOf(matchCount, bonusMatch);
        record(rank);
    }

    public void record(Rank rank) {
        result.put(rank, result.getOrDefault(rank, 0) + 1);
    }

    public void print(OutputView output) {
        output.printResultLine(Rank.FIFTH, result.getOrDefault(Rank.FIFTH, 0));
        output.printResultLine(Rank.FOURTH, result.getOrDefault(Rank.FOURTH, 0));
        output.printResultLine(Rank.THIRD, result.getOrDefault(Rank.THIRD, 0));
        output.printResultLine(Rank.SECOND, result.getOrDefault(Rank.SECOND, 0));
        output.printResultLine(Rank.FIRST, result.getOrDefault(Rank.FIRST, 0));
    }

    public double calculateEarningRate(int purchaseAmount) {
        long total = 0;

        for (Map.Entry<Rank, Integer> entry : result.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            total += (long) rank.getPrize() * count;
        }

        return (double) total / purchaseAmount;
    }
}
