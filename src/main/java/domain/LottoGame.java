package domain;

public class LottoGame {
    private int lottoTotalPrice;

    public LottoGame(int lottoTotalPrice) {
        this.lottoTotalPrice = lottoTotalPrice;
        Lottos lottos = new Lottos(calculateLottoAmount());
    }

    public int calculateLottoAmount() {
        return lottoTotalPrice / 1000;
    }

    public int getLottoTotalPrice() {
        return lottoTotalPrice;
    }
}
