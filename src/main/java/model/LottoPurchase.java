package model;

import java.util.List;

public class LottoPurchase {
    private static final int LOTTO_PRICE = 1000;
    private final int purchaseAmount;
    private final List<Lotto> manualLottos;

    public LottoPurchase(List<Lotto> manualLottos, int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException("purchaseAmount은 0보다 큰 값이여야 합니다.");
        }
        int manualCost = manualLottos.size() * LOTTO_PRICE;
        if (manualCost > purchaseAmount) {
            throw new IllegalArgumentException("입력한 수동 로또 구입 수가 구매 가능한 로또 수 보다 큽니다.");
        }
        this.manualLottos = manualLottos;
        this.purchaseAmount = purchaseAmount;
    }

    public int getAutoLottoCount() {
        return (purchaseAmount / LOTTO_PRICE) - manualLottos.size();
    }

}
