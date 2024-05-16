package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoReturnRate {
    private static final int FIRST_RANKER = 2000000000;
    private static final int SECOND_RANKER = 1500000;
    private static final int THIRD_RANKER = 50000;
    private static final int FOURTH_RANKER = 5000;
    private static final int INITIAL_NUMBER = 0;
    private final List<Integer> lottoPrice = new ArrayList<>();
    private final List<Integer> lottoRank;
    private final int getLottoMoney;

    public LottoReturnRate(List<Integer> lottoRank, int getLottoMoney) {
        this.lottoRank = lottoRank;
        this.getLottoMoney = getLottoMoney;
    }

    private void makeLottoPrice() {
        lottoPrice.add(FOURTH_RANKER);
        lottoPrice.add(THIRD_RANKER);
        lottoPrice.add(SECOND_RANKER);
        lottoPrice.add(FIRST_RANKER);
    }

    public double calculateLottoReturnRate() {
        makeLottoPrice();
        int sumOfLottoMoney = INITIAL_NUMBER;
        for (int i = INITIAL_NUMBER; i < lottoPrice.size(); i++) {
            sumOfLottoMoney += lottoRank.get(i) * lottoPrice.get(i);
        }
        return (double) sumOfLottoMoney / getLottoMoney;
    }

    public List<Integer> getLottoPrice() {
        return lottoPrice;
    }
}
