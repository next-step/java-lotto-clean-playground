package domain;

public class RateOfReturnCalculator {

    public double calculateRateOfReturn(final int buyingCosts) {
        return (double) calculateTotalPrize() / (double) buyingCosts;
    }

    private int calculateTotalPrize() {
        int totalPrize = 0;
        for (WinningLottos winningLottos : WinningLottos.values()) {
            totalPrize += winningLottos.getPrizeMoney() * winningLottos.getLottoCount();
        }
        return totalPrize;
    }
}
