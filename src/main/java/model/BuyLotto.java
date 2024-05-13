package model;

import java.util.List;

public class BuyLotto {
    private LottoMoney totalPrice;
    private int manualLottoCount;
    private List<String> manualLottoList;

    public BuyLotto(int totalPrice, int manualLottoCount, List<String> manualLottoList) {
        this.totalPrice = new LottoMoney(String.valueOf(totalPrice));
        this.manualLottoCount = manualLottoCount;
        this.manualLottoList = manualLottoList;
    }

    public int calculateAutoLottoCount() {
        return totalPrice.getAmount() / Constant.LOTTO_PRICE - manualLottoCount;
    }

    public List<String> getManualLottoList() {
        return manualLottoList;
    }
}
