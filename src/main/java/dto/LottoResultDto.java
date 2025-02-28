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

    @Override
    public String toString() {
        if (bonusBallEqual) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)- %d개", equalCount, prizeAmount, lottoAmount);
        }

        return String.format("%d개 일치 (%d원)- %d개", equalCount, prizeAmount, lottoAmount);
    }

}
