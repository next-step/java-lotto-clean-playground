package dto;

import domain.LottoRank;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record PlayLottoGameResponse(
        List<LottoRankResultDTO> lottoRankResultDTOS
) {

    public static PlayLottoGameResponse from(Map<LottoRank, Long> lottoRankResults) {
        return new PlayLottoGameResponse(
                Arrays.stream(LottoRank.values())
                        .map(result ->
                                LottoRankResultDTO.of(result, lottoRankResults.getOrDefault(result, 0L)))
                        .toList()
        );
    }
}
