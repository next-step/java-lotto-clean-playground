package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoList {
    private final List<Lotto> lottoList;

    public LottoList(List<Lotto> lottoList) {
        this.lottoList = new ArrayList<>(lottoList);
    }

    public List<Lotto> getValue() {
        return lottoList;
    }

    public int size() {
        return lottoList.size();
    }

    public LottoPrizeResult getLottoResultByWinningLottoInfo(WinningLottoInfo winningLottoInfo) {
        LottoPrizeResult lottoPrizeResult = new LottoPrizeResult();

        lottoList.forEach(lotto -> {
            Set<Integer> lottoNumbers = new HashSet<>(lotto.getValue());
            int matchNumberCount = (int) winningLottoInfo.getLottoValue().stream().filter(lottoNumbers::contains).count();
            boolean hasBonusNumber = lottoNumbers.contains(winningLottoInfo.getBonusNumberValue());

            lottoPrizeResult.addPrize(matchNumberCount, hasBonusNumber);
        });

        return lottoPrizeResult;
    }
}
