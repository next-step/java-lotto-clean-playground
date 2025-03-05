package dto;

import model.LottoResult;

public class LottoResultDto {

    private final int equalCount;
    private final int prizeAmount;
    private final int lottoAmount;
    private final boolean bonusBallEqual;

    public LottoResultDto(LottoResult lottoResult) {
        this.equalCount = lottoResult.getEqualCount();
        this.prizeAmount = lottoResult.getPrizeAmount();
        this.lottoAmount = lottoResult.getLottoAmount();
        this.bonusBallEqual = lottoResult.isBonusBallResult();
    }

    public int getEqualCount() {
        return equalCount;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public int getLottoAmount() {
        return lottoAmount;
    }

    public boolean isBonusBallEqual() {
        return bonusBallEqual;
    }

}
