package domain;

public class LottoProfit {

    public static int LottoSum(MatchCount matchCount) {
        int match3Count = matchCount.getCount(LottoPrice.MATCH_3);
        int match4Count = matchCount.getCount(LottoPrice.MATCH_4);
        int match5Count = matchCount.getCount(LottoPrice.MATCH_5);
        int match5BonusCount = matchCount.getCount(LottoPrice.MATCH_5_BONUS);
        int match6Count = matchCount.getCount(LottoPrice.MATCH_6);

        int lottoSum = (match3Count * LottoPrice.MATCH_3.getPrice()) + (match4Count * LottoPrice.MATCH_4.getPrice())
                + (match5Count * LottoPrice.MATCH_5.getPrice()) + (match5BonusCount * LottoPrice.MATCH_5_BONUS.getPrice())
                + (match6Count * LottoPrice.MATCH_6.getPrice());
        return lottoSum;
    }

    public static double LottoProfit(int money, int lottoSum) {
        return (double) lottoSum / money;
    }
}
