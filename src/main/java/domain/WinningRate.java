package domain;

import java.util.List;

public class WinningRate {

    public int calculateWinPrice(List<Integer> counts) {
        return threeWin(counts) + fourWin(counts) + fiveWin(counts) + secondWin(counts) + sixWin(counts);
    }

    private int threeWin(List<Integer> counts) {
        return countGoal(counts, WinningPrize.THREE.getGoal()) * WinningPrize.THREE.getPrize();
    }

    private int fourWin(List<Integer> counts) {
        return countGoal(counts, WinningPrize.FOUR.getGoal())
                * WinningPrize.FOUR.getPrize();
    }

    private int fiveWin(List<Integer> counts) {
        return countGoal(counts, WinningPrize.FIVE.getGoal())
                * WinningPrize.FIVE.getPrize();
    }

    private int secondWin(List<Integer> counts) {
        return countGoal(counts, WinningPrize.BONUS.getGoal())
                * WinningPrize.BONUS.getPrize();
    }

    private int sixWin(List<Integer> counts) {
        return countGoal(counts, WinningPrize.SIX.getGoal())
                * WinningPrize.SIX.getPrize();
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
