package domain;

import view.OutputView;

import java.util.List;

public class LottoGame {
    private int lottoTotalPrice;
    private Lottos lottos;

    public LottoGame(int lottoTotalPrice) {
        this.lottoTotalPrice = lottoTotalPrice;
        this.lottos = new Lottos(calculateLottoAmount());
    }


    public int calculateLottoAmount() {
        return lottoTotalPrice / 1000;
    }

    public int getLottoTotalPrice() {
        return lottoTotalPrice;
    }
    public List<Lotto> getLottos(){
        return lottos.getLottos();
    }

}
