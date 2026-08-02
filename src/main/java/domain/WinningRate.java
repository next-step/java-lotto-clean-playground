package domain;

import java.util.List;

public class WinningRate {

    public int calculateWinPrice(List<Integer> counts) {
        return threeWin(counts) + fourWin(counts) + fiveWin(counts) + sixWin(counts);
    }

    private int threeWin(List<Integer> counts) {
        return countGoal(counts, 3) * 5000;
    }

    private int fourWin(List<Integer> counts) {
        return countGoal(counts, 4) * 5000;
    }

    private int fiveWin(List<Integer> counts) {
        return countGoal(counts, 5) * 5000;
    }
    private int sixWin(List<Integer> counts) {
        return countGoal(counts, 6) * 5000;
    }

    private int countGoal(List<Integer> counts, int goal) {
        int count = 0;

        for (int i = 0; i < counts.size(); i++) {
            if (goal == counts.get(i)) count++;
        }
        return count;
    }

    public double calculateRate(int winPrice, int purchasePrice) {
        return (double) winPrice / purchasePrice;
    }
}
