package model;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class LottoFinanceStatsCalculator {
    private final LottoBatch lottoBatch;
    private final WinCondition winCondition;

    public LottoFinanceStatsCalculator(LottoBatch lottoBatch, WinCondition winCondition) {
        this.lottoBatch = lottoBatch;
        this.winCondition = winCondition;
    }

    private double calculateReturnRatio() {
        List<LottoResult> lottoResults = this.calculateMatchCountPerLotto();

        double earnResult = 0.0;
        for (LottoResult lottoResult : lottoResults) {
            earnResult += lottoResult.getReward();
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * this.lottoBatch.getAllLotto().size());
    }

    private List<LottoResult> calculateMatchCountPerLotto() {
        List<LottoResult> result = new ArrayList<>();

        for (Lotto lotto : this.lottoBatch.getAllLotto()) {
            result.add(lotto.compareWithWinCondition(winCondition));
        }

        return result;
    }

    public double getReturnRatio() {
        return this.calculateReturnRatio();
    }

    public List<LottoResult> getLottoResults() {
        return List.copyOf(this.calculateMatchCountPerLotto());
    }
}
