package model;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.List;

public class LottoFinalResult {
    private final LottoBatch lottoBatch;
    private final WinCondition winCondition;

    private final List<LottoResult> lottoResults;
    private final double returnRatio;

    LottoFinalResult(LottoBatch lottoBatch, WinCondition winCondition) {
        this.lottoBatch = lottoBatch;
        this.winCondition = winCondition;

        this.lottoResults = this.calculateMatchCountPerLotto(this.winCondition);
        this.returnRatio = this.calculateReturnRatio();
    }

    private double calculateReturnRatio() {
        double earnResult = 0.0;
        for (LottoResult lottoResult : this.lottoResults) {
            earnResult += lottoResult.getReward();
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * lottoBatch.getLottoCount());
    }

    private List<LottoResult> calculateMatchCountPerLotto(WinCondition winCondition) {
        List<LottoResult> result = new ArrayList<>();

        for (Lotto lotto : lottoBatch.getAllLotto()) {
            result.add(lotto.compareWithWinCondition(winCondition));
        }

        return result;
    }

    public double getReturnRaion() {
        return this.returnRatio;
    }

    public List<LottoResult> lottoResults() {
        return List.copyOf(this.lottoResults);
    }
}
