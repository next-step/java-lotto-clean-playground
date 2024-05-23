package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Random;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto() {
        int lottoMoney = getLottoMoney();
        Lottos lottos = buyLotto(lottoMoney);
        List<Integer> lastWeekLottoNumber = getLastWeekLottoNumber();
        rankLotto(lottos, lastWeekLottoNumber, lottoMoney);
    }

    private int getLottoMoney() {
        outputView.printGetLottoMoney();
        return inputView.getLottoMoney();
    }

    private Lottos buyLotto(int getLottoMoney) {
        Random randomNumberGenerator = new Random();
        outputView.printLottoCount(getLottoMoney);
        CreateLottoNumber createLottoNumber = new LottoNumberGenerator(randomNumberGenerator);
        Lottos lottos = new Lottos(getLottoMoney, createLottoNumber);
        for (Lotto lotto : lottos.getLottos()) {
            outputView.printLotto(lotto.getLottoNumber());
        }
        return lottos;
    }

    private List<Integer> getLastWeekLottoNumber() {
        outputView.LastWeekLottoNumber();
        String inputLastWeekLottoNumber = inputView.inputLastWeekLottoNumber();
        LastWeekLottoNumber lottoNumber = new LastWeekLottoNumber(inputLastWeekLottoNumber);
        return lottoNumber.getLastWeekLottoNumber();
    }

    private void rankLotto(Lottos lottos, List<Integer> lastWeekLottoNumber, int getLottoMoney) {
        LottosRank lottosRank = new LottosRank(lottos, lastWeekLottoNumber);
        List<Integer> lottoRank = lottosRank.getRankLottos();
        LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRank, getLottoMoney);
        outputView.printLottoStatistics();
        double returnRate = lottoReturnRate.calculateLottoReturnRate();
        outputView.printLottoRanker(lottoRank, lottoReturnRate.makeLottoPrice());
        outputView.printRateOfReturn(returnRate);
    }
}
