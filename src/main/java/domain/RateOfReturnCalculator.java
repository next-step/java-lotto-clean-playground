package domain;

public class RateOfReturnCalculator {

    public double calculateRateOfReturn(final int buyingCosts) {
        return (double) calculateTotalPrize() / (double) buyingCosts;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;
        for (WinningLottosStatus winningLottos : WinningLottosStatus.values()) {
            totalPrize += winningLottos.getPrizeMoney() * winningLottos.getLottoCount();
        }
        return totalPrize;
    }
}
