package controller;

import domain.Lotto;
import view.InputView;
import view.OutputView;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void buyLotto(){
        outputView.printGetLottoMoney();
        Lotto buyLotto = new Lotto(inputView.getLottoMoney());
        outputView.printLottoCount(buyLotto.getLottoCount());
        buyLotto.makeLottoNumber();
        List<List<Integer>> sumOfLotto = buyLotto.sumOfLotto();
        outputView.printSumOfLotto(sumOfLotto);
    }
}
