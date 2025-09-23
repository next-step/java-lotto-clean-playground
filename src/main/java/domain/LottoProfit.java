package domain;

import java.util.List;

public class LottoProfit {

    public static int LottoSum(MatchCount matchCount) {
        int match3Count = matchCount.getMatch3Count();
        int match4Count = matchCount.getMatch4Count();
        int match5Count = matchCount.getMatch5Count();
        int match6Count = matchCount.getMatch6Count();

        int lottoSum=(match3Count * 5000) + (match4Count * 50000) + (match5Count * 1500000) + (match6Count * 2000000000);
        return lottoSum;
    }

    public static double LottoProfit(int money, int lottoSum) {
        return (double) lottoSum / money;
    }
}
