package service;

import domain.Bingo;

public class CalculateProfit {
    private static double profit = 0;

    public static void calcProfit() {
        profit += 5000 * Bingo.getBingo3().getCorrectLottoNum();
        profit += 50000 * Bingo.getBingo4().getCorrectLottoNum();
        profit += 1500000 * Bingo.getBingo5().getCorrectLottoNum();
        profit += 30000000 * Bingo.getBingo5wB().getCorrectLottoNum();
        profit += 2000000000 * Bingo.getBingo6().getCorrectLottoNum();
    }

    public static double calcProfitPercent(int money) {
        double percent = (profit / (double) money);
        return Math.round((int) (percent * 100)) / 100.0;
    }
}
