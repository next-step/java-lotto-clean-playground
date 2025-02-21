package model;

public class LottoResult implements Comparable<LottoResult> {

    private final LottoRank lottoRank;
    private int lottoAmount;

    public LottoResult(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
        this.lottoAmount = 0;
    }

    public void increaseLottoAmount() {
        lottoAmount++;
    }

    public int getEqualCount() {
        return lottoRank.equalCount;
    }

    public int getPrizeAmount() {
        return lottoRank.prizeAmount;
    }

    public int getLottoAmount() {
        return lottoAmount;
    }

    public boolean isBonusBallResult() {
        return lottoRank == LottoRank.FIVE_WITH_BONUS_EQUALS;
    }

    public int getTotalPrizeAmount() {
        return lottoRank.prizeAmount * lottoAmount;
    }

    @Override
    public int compareTo(LottoResult other) {
        return this.lottoRank.compareTo(other.lottoRank);
    }

}
