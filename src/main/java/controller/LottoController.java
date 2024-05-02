package controller;

import model.LottoGenerator;
import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    public void lottoStart(){
        LottoGenerator lottoGenerator = new LottoGenerator(InputView.getPrice());
        List<Lotto> lottos = lottoGenerator.generateLotto();
        ResultView.printLottos(lottos);
    }

}
