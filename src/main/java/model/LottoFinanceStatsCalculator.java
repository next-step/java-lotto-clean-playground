package model;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.List;

public class LottoFinanceStatsCalculator {
    private final LottoBatch lottoBatch;

    public LottoFinanceStatsCalculator(LottoBatch lottoBatch) {
        this.lottoBatch = lottoBatch;
    }

    private double calculateReturnRatio(WinCondition winCondition) {
        List<LottoResult> lottoResults = this.calculateMatchCountPerLotto(winCondition);

        double earnResult = 0.0;
        for (LottoResult lottoResult : lottoResults) {
            earnResult += lottoResult.getReward();
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * this.lottoBatch.getAllLotto().size());
    }

    private List<LottoResult> calculateMatchCountPerLotto(WinCondition winCondition) {
        List<LottoResult> result = new ArrayList<>();

        for (Lotto lotto : this.lottoBatch.getAllLotto()) {
            result.add(lotto.compareWithWinCondition(winCondition));
        }

        return result;
    }

    public double getReturnRatio(WinCondition winCondition) {
        return this.calculateReturnRatio(winCondition);
    }

    public List<LottoResult> getLottoResults(WinCondition winCondition) {
        return List.copyOf(this.calculateMatchCountPerLotto(winCondition));
    }
}
