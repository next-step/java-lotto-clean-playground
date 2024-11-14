package domain;

public class LottoCalculator {

    private static final int LOTTO_COST = 1000;

    public int calculateAutoLottoCount(final int buyingCosts, final int passiveLottoCount) {
        return (buyingCosts - passiveLottoCount * LOTTO_COST) / LOTTO_COST;
    }

    public double calculateRateOfReturn(final int buyingCosts, final WinningLottoCount winningLottoCount) {
        return (double) calculateTotalPrize(winningLottoCount) / buyingCosts;
    }

    private long calculateTotalPrize(final WinningLottoCount winningLottoCount) {
        // int의 범위를 넘어가는 상금 가능성 존재 -> long으로 변경
        long totalPrize = 0;
        for (WinningLottosStatus winningLottos : winningLottoCount.getWinningLottoCountStatus().keySet()) {
            totalPrize += (long) winningLottos.getPrizeMoney() * winningLottoCount.getWinningLottoCountStatus().get(winningLottos);
        }
        return totalPrize;
    }
}
