package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

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
        outputView.printLottoCount(getLottoMoney);
        CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
        Lottos lottos = new Lottos(createLottoNumber, getLottoMoney);
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
        LottoRank lottoRank = new LottoRank(lottos, lastWeekLottoNumber);
        Map<String, Integer> lottoRanks = lottoRank.getLottoRank();
        LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRanks, getLottoMoney);
        outputView.printLottoStatistics();
        outputView.printLottoRank(LottoPrice.getLottoMessageBundle(), lottoRanks, LottoPrice.getSameLottoNumberBundle());
        outputView.printRateOfReturn(lottoReturnRate.getLottoReturnRate());
    }
}
