package controller;

import domain.BuyLotto;
import view.InputView;
import view.OutputView;
import java.util.List;

public class BuyLottoControlller {

    private final InputView inputView;
    private final OutputView outputView;

    public BuyLottoControlller(InputView inputView, OutputView outputView){
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void buyLotto(){
        outputView.printGetLottoMoney();
        BuyLotto buyLotto = new BuyLotto(inputView.getLottoMoney());
        outputView.printLottoCount(buyLotto.getLottoCount());
        buyLotto.makeLottoNumber();
        List<List<Integer>> sumOfLotto = buyLotto.sumOfLotto();
        outputView.printSumOfLotto(sumOfLotto);
    }
}
