package domain;

public class RateOfReturnCalculator {

    public double calculateRateOfReturn(final int buyingCosts) {
        return (double) calculateTotalPrize() / (double) buyingCosts;
    }

    private long calculateTotalPrize() {
        // int로 하려했었는데 int의 범위를 넘어가는 상금을 받을 수 있음이 존재 -> long으로 변경
        long totalPrize = 0;
        for (WinningLottosStatus winningLottos : WinningLottosStatus.values()) {
            totalPrize += winningLottos.getPrizeMoney() * winningLottos.getLottoCount();
        }
        return totalPrize;
    }
}
