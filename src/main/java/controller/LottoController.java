package controller;

import domain.Lotto;
import domain.Lottos;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoController {

    private static final int POSSIBLE_LOTTO_DELIMITER = 1000;
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void buyLotto(){
        outputView.printGetLottoMoney();
        int getLottoMoney = inputView.getLottoMoney();
        outputView.printLottoCount(countPossibleLotto(getLottoMoney));
        Lottos lottos = Lottos.makeLottos(countPossibleLotto(getLottoMoney));
        outputView.printSumOfLotto(lottos);
    }

    private int countPossibleLotto(int getLottoMoney){
        return getLottoMoney/POSSIBLE_LOTTO_DELIMITER;
    }
}
