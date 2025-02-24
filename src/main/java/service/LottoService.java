package service;

import domain.*;
import dto.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.LottoConstant.LOTTO_PRICE;

public class LottoService {

    private final LottoStore lottoStore;

    public LottoService(LottoStore lottoStore) {
        this.lottoStore = lottoStore;
    }

    public GetLottoCountResponse getLottoCount(long amount) {
        return GetLottoCountResponse.from(lottoStore.getLottoCount(amount));
    }

    public PurchaseLottosResponse purchaseLottos(List<Lotto> passivityLottos, long automaticCount) {
        LottoGroup lottoGroup = lottoStore.buyLottos(passivityLottos, automaticCount);

        return PurchaseLottosResponse.from(lottoGroup);
    }

    public PlayLottoGameResponse playLottoGame(LottoGroup userLottoGroup, WinLotto winLotto) {
        List<Lotto> lottos = userLottoGroup.getLottos();
        Map<LottoRank, Long> lottoRankResult = calculateLottoResults(winLotto, lottos);

        return PlayLottoGameResponse.from(lottoRankResult);
    }

    private Map<LottoRank, Long> calculateLottoResults(WinLotto winLotto, List<Lotto> lottos) {
        return lottos.stream()
                .map(winLotto::calculateRank)
                .collect(Collectors.groupingBy(
                        rank -> rank, Collectors.counting()
                ));
    }

    public CalculateEarningRateResponse calculateEarningsRate(List<LottoRankResultDTO> results) {
        long count = calculateLottoCount(results);

        return CalculateEarningRateResponse.from(
                (double) calculatePrizeMoney(results) / calculatePurchaseMoney(count));
    }

    private long calculateLottoCount(List<LottoRankResultDTO> results) {
        return results.stream()
                .mapToLong(LottoRankResultDTO::resultCount)
                .sum();
    }

    private long calculatePrizeMoney(List<LottoRankResultDTO> results) {
        return results.stream()
                .mapToLong(result -> result.prize() * result.resultCount())
                .sum();
    }

    private long calculatePurchaseMoney(long count) {
        return count * LOTTO_PRICE;
    }
}
