package domain;

import java.util.List;
import java.util.Map;

public class LottoReturnRate {

    private static final int RESET_NUMBER = 0;
    private static final double MAKE_RETURN_RATE_DEVIDE_NUMBER = 100.0;
    private static final int MAKE_RETURN_RATE_MULTIPLE_NUMBER = 100;

    private final double lottoReturnRate;

    public LottoReturnRate(Map<String, Integer> lottoRank, int getLottoMoney) {
        this.lottoReturnRate = calculateLottoReturnRate(lottoRank, getLottoMoney);
    }

    public double getLottoReturnRate() {
        return lottoReturnRate;
    }

    private double calculateLottoReturnRate(Map<String, Integer> lottoRank, int getLottoMoney) {
        List<Integer> lottoRankPrice = LottoPrice.getLottoPriceBundle();
        List<String> sameLottoNumbers = LottoPrice.getSameLottoNumberBundle();
        int sumOfLottoMoney = RESET_NUMBER;
        int count = RESET_NUMBER;
        for (String sameLottoNumber : sameLottoNumbers) {
            sumOfLottoMoney += lottoRank.get(sameLottoNumber) * lottoRankPrice.get(count);
            count++;
        }
        double lottoReturnRate = (double) sumOfLottoMoney / getLottoMoney;
        return Math.floor(lottoReturnRate * MAKE_RETURN_RATE_MULTIPLE_NUMBER) / MAKE_RETURN_RATE_DEVIDE_NUMBER;
    }
}
