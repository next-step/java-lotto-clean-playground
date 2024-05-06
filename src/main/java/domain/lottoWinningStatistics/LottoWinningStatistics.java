package domain.lottoWinningStatistics;

import java.util.List;

public class LottoWinningStatistics {
    private final List<Integer> lottoWinningNumbers;
    private final List<Integer> lottoWinningCount;
    private final double returnOfInvestment;

    public LottoWinningStatistics(List<Integer> lottoWinningNumbers,
                                  List<Integer> lottoWinningCount,
                                  double returnOfInvestment) {
        this.lottoWinningNumbers =lottoWinningNumbers;
        this.lottoWinningCount = lottoWinningCount;
        this.returnOfInvestment = returnOfInvestment;
    }

    public List<Integer> getLottoWinnerNumbers() {
        return this.lottoWinningNumbers;
    }

    public List<Integer> getLottoWinnerCount() {
        return this.lottoWinningCount;
    }

    public double getReturnOfInvestment() {
        return this.returnOfInvestment;
    }
}
