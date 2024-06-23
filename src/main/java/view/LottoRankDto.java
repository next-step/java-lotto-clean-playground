package view;

import domain.LottoPrice;

public class LottoRankDto {

    private final String lottoRank;
    private final int lottoRankerNumber;

    public LottoRankDto(String lottoRank, int lottoRankerNumber) {
        this.lottoRank = lottoRank;
        this.lottoRankerNumber = lottoRankerNumber;
    }

    @Override
    public String toString() {
        if (lottoRank.equals("5BONUS")) {
            return "5개 일치, 보너스 볼 일치(" + LottoPrice.getLottoPrice(lottoRank) + ")- " + lottoRankerNumber + "개";
        }
        return lottoRank + "개 일치 (" + LottoPrice.getLottoPrice(lottoRank) + ")- " + lottoRankerNumber + "개";
    }
}
