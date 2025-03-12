package dto;

import model.LottoRank;

public class LottoResultDto {

    private final int matchCount;
    private final boolean bonusMatch;
    private final int price;
    private final long count;

    public LottoResultDto(LottoRank lottoRank, long count) {
        this.matchCount = lottoRank.getMatchCount();
        this.bonusMatch = lottoRank.isMatchBonus();
        this.price = lottoRank.getPrice();
        this.count = count;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public int getPrice() {
        return price;
    }

    public long getCount() {
        return count;
    }
}
