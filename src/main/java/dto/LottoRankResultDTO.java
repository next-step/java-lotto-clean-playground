package dto;

import domain.LottoRank;

public record LottoRankResultDTO(
        int matchedCount,
        long prize,
        long resultCount
) {

    public static LottoRankResultDTO of(LottoRank lottoRank, long resultCount) {
        return new LottoRankResultDTO(lottoRank.getMatchedCount(), lottoRank.getPrize(), resultCount);
    }
}
