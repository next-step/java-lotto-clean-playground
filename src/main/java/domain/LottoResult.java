package domain;

import java.util.ArrayList;
import java.util.HashMap;
import vo.Lotto;
import vo.LottoNumber;

public class LottoResult {

    private HashMap<WinningRank, Integer> winningStatistics = new HashMap<>();
    private int totalInvestment;

    public LottoResult() {
        initializeWinningStatistics();
    }

    private void initializeWinningStatistics() {
        for (WinningRank rank : WinningRank.values()) {
            winningStatistics.put(rank, 0);
        }
    }

    // 로또 결과를 계산하는 메서드
    public void calculateResult(ArrayList<Lotto> buyLottos, Lotto winningLotto, LottoNumber bonusBall, int investment) {
        validateBonusBall(winningLotto, bonusBall);
        this.totalInvestment = investment;
        for (Lotto lotto : buyLottos) {
            addWinningResult(lotto, winningLotto, bonusBall);
        }
    }

    private void validateBonusBall(Lotto winningLotto, LottoNumber bonusBall) {
        if (winningLotto.getNumbers().contains(bonusBall)) {
            throw new IllegalArgumentException("보너스볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private void addWinningResult(Lotto lotto, Lotto winningLotto, LottoNumber bonusBall) {
        int matchCount = winningLotto.countMatchingNumbers(lotto);
        boolean bonusBallMatch = lotto.getNumbers().contains(bonusBall);
        WinningRank rank = WinningRank.valueOf(matchCount, bonusBallMatch);
        winningStatistics.put(rank, winningStatistics.get(rank) + 1);
    }

    public HashMap<WinningRank, Integer> getWinningStatistics() {
        return new HashMap<>(winningStatistics);
    }

    // 수익률을 계산하는 메서드
    public double calculateProfitRate() {
        int totalPrize = calculateTotalPrize();
        return (double) totalPrize / totalInvestment;
    }

    private int calculateTotalPrize() {
        int totalPrize = 0;
        for (WinningRank rank : winningStatistics.keySet()) {
            totalPrize += rank.getPrize() * winningStatistics.get(rank);
        }
        return totalPrize;
    }
}
