package controller;

import domain.LastWeekLottoNumber;
import domain.LottoRank;
import domain.LottoReturnRate;
import domain.Lottos;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private static final int BUYABLE_LOTTO_DELIMITER = 1000;
    private final InputView inputView;
    private final OutputView outputView;

    private int getLottoMoney;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto() {
        getLottoMoney();
        Lottos lottos = buyLotto(getLottoMoney);
        List<Integer> lastWeekLottoNumber = getLastWeekLottoNumber();
        rankLotto(lottos, lastWeekLottoNumber);
    }

    private void getLottoMoney() {
        outputView.printGetLottoMoney();
        getLottoMoney = inputView.getLottoMoney();
    }

    private int countBuyableLotto(int getLottoMoney) {
        return getLottoMoney / BUYABLE_LOTTO_DELIMITER;
    }

    private Lottos buyLotto(int getLottoMoney) {
        outputView.printLottoCount(countBuyableLotto(getLottoMoney));
        Lottos lottos = new Lottos(countBuyableLotto(getLottoMoney));
        outputView.printSumOfLotto(lottos);
        return lottos;
    }

    private List<Integer> getLastWeekLottoNumber() {
        outputView.LastWeekLottoNumber();
        String inputLastWeekLottoNumber = inputView.inputLastWeekLottoNumber();
        LastWeekLottoNumber lottoNumber = new LastWeekLottoNumber(inputLastWeekLottoNumber);
        lottoNumber.makeLastWeekLottoNumberList();
        return lottoNumber.getLastWeekLottoNumber();
    }

    private void rankLotto(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        LottoRank lottoRank = new LottoRank(lottos);
        lottoRank.countCorrectLottoNumber(lastWeekLottoNumber);
        lottoRank.rankLotto();
        LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank.getLottoRank(), getLottoMoney);
        outputView.printLottoStatistics();
        double returnRate = lottoReturnRate.calculateLottoReturnRate();
        outputView.printLottoRanker(lottoRank.getLottoRank(), lottoReturnRate.getLottoPrice());
        outputView.printRateOfReturn(returnRate);
    }
}
