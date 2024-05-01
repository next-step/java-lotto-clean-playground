package controller;

import model.BuyLotto;
import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void lottoStart(){

        BuyLotto buyLotto = new BuyLotto(InputView.getPrice());
        List<Lotto> lottos = buyLotto
                .generateLotto();

        ResultView.printLottos(lottos);

    }


}
