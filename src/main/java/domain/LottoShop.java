package domain;

public class LottoShop {
    private final int inputMoney;
    private final static int LOTTO_PRICE = 1000;

    public LottoShop(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public int countLottoTickets(int inputMoney) {
        return inputMoney / 1000;
    }
}
