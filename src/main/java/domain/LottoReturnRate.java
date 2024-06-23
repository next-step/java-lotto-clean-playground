package domain;

import java.util.List;

public class LottoReturnRate {

    private static final int RESET_NUMBER = 0;
    private static final double MAKE_RETURN_RATE_DEVIDE_NUMBER = 100.0;
    private static final int MAKE_RETURN_RATE_MULTIPLE_NUMBER = 100;

    private final double lottoReturnRate;

    public LottoReturnRate(List<LottoRank> lottoRanks, int getLottoMoney) {
        this.lottoReturnRate = calculateLottoReturnRate(lottoRanks, getLottoMoney);
    }

    public double getLottoReturnRate() {
        return lottoReturnRate;
    }

    private double calculateLottoReturnRate(List<LottoRank> lottoRanks, int getLottoMoney) {
        List<Integer> lottoRankPrice = LottoPrice.getLottoPriceBundle();
        int sumOfLottoMoney = RESET_NUMBER;
        int count = RESET_NUMBER;
        for (LottoRank lottoRank : lottoRanks) {
            sumOfLottoMoney += lottoRank.getLottoRankNumber() * lottoRankPrice.get(count);
            count++;
        }
        double lottoReturnRate = (double) sumOfLottoMoney / getLottoMoney;
        return Math.floor(lottoReturnRate * MAKE_RETURN_RATE_MULTIPLE_NUMBER) / MAKE_RETURN_RATE_DEVIDE_NUMBER;
    }
}
