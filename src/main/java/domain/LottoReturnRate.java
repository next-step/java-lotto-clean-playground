package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoReturnRate {
    private static final int FIRST_RANKER_PRICE = 2000000000;
    private static final int SECOND_RANKER_PRICE = 1500000;
    private static final int THIRD_RANKER_PRICE = 50000;
    private static final int FOURTH_RANKER_PRICE = 5000;
    private static final int INITIAL_NUMBER = 0;
    private static final double MAKE_RETURN_RATE_DEVIDE_NUMBER = 100.0;
    private static final int MAKE_RETURN_RATE_MULTIPLE_NUMBER = 100;
    private final List<Integer> lottoRank;
    private final int getLottoMoney;

    public LottoReturnRate(List<Integer> lottoRank, int getLottoMoney) {
        this.lottoRank = lottoRank;
        this.getLottoMoney = getLottoMoney;
    }

    public List<Integer> makeLottoPrice() {
        return List.of(FOURTH_RANKER_PRICE, THIRD_RANKER_PRICE, SECOND_RANKER_PRICE, FIRST_RANKER_PRICE);
    }

    public double calculateLottoReturnRate() {
        List<Integer> lottoRankPrice = makeLottoPrice();
        int sumOfLottoMoney = INITIAL_NUMBER;
        for (int i = INITIAL_NUMBER; i < lottoRankPrice.size(); i++) {
            sumOfLottoMoney += lottoRank.get(i) * lottoRankPrice.get(i);
        }
        double realLottoReturnRate = (double) sumOfLottoMoney / getLottoMoney;
        return Math.floor(realLottoReturnRate * MAKE_RETURN_RATE_MULTIPLE_NUMBER) / MAKE_RETURN_RATE_DEVIDE_NUMBER;
    }
}
