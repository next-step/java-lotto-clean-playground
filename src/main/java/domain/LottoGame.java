package domain;

import view.OutputView;

public class LottoGame {
    private int lottoTotalPrice;
    private Lottos lottos;

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

    public Lottos getLottos() {
        return lottos;
    }
    public void printLottoNumbers() {
        OutputView.printLottos(lottos.getLottos());
    }
}
