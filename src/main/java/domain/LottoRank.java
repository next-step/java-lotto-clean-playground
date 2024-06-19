package domain;

public class LottoRank {

    private final String lottoRank;
    private final int lottoRankNumber;

    public LottoRank(String lottoRank, int lottoRankNumber) {
        this.lottoRank = lottoRank;
        this.lottoRankNumber = lottoRankNumber;
    }

    public String getLottoRank() {
        return lottoRank;
    }

    public int getLottoRankNumber() {
        return lottoRankNumber;
    }
}
