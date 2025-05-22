package model;

import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;

    public LottoPurchase(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException("purchaseAmount은 0보다 큰 값이여야 합니다.");
        }
        this.purchaseAmount = purchaseAmount;
    }

    public int getTotalCount(){
        return purchaseAmount/LOTTO_PRICE;
    }

    public void validateManualCount(int manualCount){
        if (manualCount >getTotalCount()) {
            throw new IllegalArgumentException("수동 로또 수가 구매 가능한 범위를 초과합니다.");
        }
    }

    public int getAutoCount(int manualCount){
        validateManualCount(manualCount);
        return getTotalCount() - manualCount;
    }

}
