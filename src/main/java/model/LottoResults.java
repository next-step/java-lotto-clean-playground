package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResults {

    private final List<LottoResult> lottoResults;

    public LottoResults(Lottos lottos, LottoNumbers winningLottoNumbers, LottoNumber bonusBall) {
        Map<LottoRank, LottoResult> lottoResultMap = initializeLottoResultMap();

        for (LottoNumbers lotto : lottos.getLottoNumbersCollection()) {
            LottoRank lottoRank = calculateLottoPrize(lotto, winningLottoNumbers, bonusBall);
            updateLottoResult(lottoResultMap, lottoRank);
        }

        removeUnnecessaryLottoResult(lottoResultMap);

        this.lottoResults = convertMapIntoSortedList(lottoResultMap);
    }

    public double getTotalProfitRate(int purchaseAmount) {
        return getTotalLottoPrize() / (double) purchaseAmount;
    }

    public List<LottoResult> getLottoResultList() {
        return List.copyOf(lottoResults);
    }

    private Map<LottoRank, LottoResult> initializeLottoResultMap() {
        Map<LottoRank, LottoResult> lottoResultMap = new HashMap<>();

        for (LottoRank lottoRank : LottoRank.values()) {
            lottoResultMap.put(lottoRank, new LottoResult(lottoRank));
        }

        return lottoResultMap;
    }

    private LottoRank calculateLottoPrize(LottoNumbers targetLotto, LottoNumbers winningLotto, LottoNumber bonusBall) {
        int equalNumberCount = targetLotto.getEqualNumbersCount(winningLotto);
        boolean containBonusBall = targetLotto.hasEqualNumberWithBonusBall(bonusBall);

        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.isCorrectPrize(equalNumberCount, containBonusBall))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Lotto의 상금을 계산할 수 없습니다."));
    }

    private void updateLottoResult(Map<LottoRank, LottoResult> lottoResultMap, LottoRank lottoRank) {
        LottoResult lottoResult = lottoResultMap.get(lottoRank);
        lottoResult.increaseLottoAmount();
    }

    private void removeUnnecessaryLottoResult(Map<LottoRank, LottoResult> lottoResultMap) {
        lottoResultMap.remove(LottoRank.NO_PRIZE);
    }

    private List<LottoResult> convertMapIntoSortedList(Map<LottoRank, LottoResult> lottoResultMap) {
        return lottoResultMap.values()
                .stream()
                .sorted()
                .toList();
    }

    private int getTotalLottoPrize() {
        int sum = 0;

        for (LottoResult lottoResult : lottoResults) {
            sum += lottoResult.getTotalPrizeAmount();
        }

        return sum;
    }

}
