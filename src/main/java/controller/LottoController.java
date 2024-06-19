package controller;

import domain.*;
import view.InputView;
import view.LottoRankDto;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int RESET_NUMBER = 0;

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
        outputView.printPassiveLottoCount();
        int passiveLottoNumberCount = inputView.getPassiveLottoCount();
        outputView.printPassiveLottoNumber();
        List<String> passiveLottoNumbers = new ArrayList<>();
        for (int i = RESET_NUMBER; i < passiveLottoNumberCount; i++) {
            String passiveLottoNumber = inputView.inputLottoNumber();
            passiveLottoNumbers.add(passiveLottoNumber);
        }
        PassiveLottoNumber passiveLottoNumber = new PassiveLottoNumber(passiveLottoNumbers);
        outputView.printLottoCount(getLottoMoney, passiveLottoNumberCount);
        CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
        Lottos lottos = new Lottos(createLottoNumber, passiveLottoNumber.getPassiveLottoNumbers(), getLottoMoney);
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> lottoNumber = lotto.getLottoNumber().stream().map(LottoNumber::getLottoNumber).toList();
            outputView.printLotto(lottoNumber);
        }
        return lottos;
    }

    private List<Integer> getLastWeekLottoNumber() {
        outputView.LastWeekLottoNumber();
        String inputLastWeekLottoNumber = inputView.inputLottoNumber();
        LottoNumberParser lottoNumberParser = new LottoNumberParser(inputLastWeekLottoNumber);
        int bonusBall = getBonusBall();
        lottoNumberParser.addBonusBall(bonusBall);
        return lottoNumberParser.getRealLottoNumber();
    }

    private int getBonusBall() {
        outputView.inputBonusBall();
        return inputView.inputBonusBall();
    }

    private void rankLotto(Lottos lottos, List<Integer> lastWeekLottoNumber, int getLottoMoney) {
        LottoRankBundle lottoRankBundle = new LottoRankBundle(lottos, lastWeekLottoNumber);
        LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRankBundle.getLottoRank(), getLottoMoney);
        outputView.printLottoStatistics();
        for(LottoRank lottoRank : lottoRankBundle.getLottoRank()){
            LottoRankDto lottoRankDto = new LottoRankDto(lottoRank.getLottoRank(), lottoRank.getLottoRankNumber());
            outputView.printLottoRank(lottoRankDto.toString());
        }
        outputView.printRateOfReturn(lottoReturnRate.getLottoReturnRate());
    }
}
