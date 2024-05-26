package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LottoReturnRate {

    private static final int RESET_NUMBER = 0;
    private static final double MAKE_RETURN_RATE_DEVIDE_NUMBER = 100.0;
    private static final int MAKE_RETURN_RATE_MULTIPLE_NUMBER = 100;

    private final double lottoReturnRate;

    public LottoReturnRate(List<Integer> lottoRank, int getLottoMoney) {
        this.lottoReturnRate = calculateLottoReturnRate(lottoRank, getLottoMoney);
    }

    public double getLottoReturnRate() {
        return lottoReturnRate;
    }

    private double calculateLottoReturnRate(List<Integer> lottoRank, int getLottoMoney) {
        List<Integer> lottoRankPrice = LottoPrice.getLottoPriceBundle();
        int sumOfLottoMoney = RESET_NUMBER;
        for (int i = RESET_NUMBER; i < lottoRankPrice.size(); i++) {
            sumOfLottoMoney += lottoRank.get(i) * lottoRankPrice.get(i);
        }
        double lottoReturnRate = (double) sumOfLottoMoney / getLottoMoney;
        return Math.floor(lottoReturnRate * MAKE_RETURN_RATE_MULTIPLE_NUMBER) / MAKE_RETURN_RATE_DEVIDE_NUMBER;
    }
}
