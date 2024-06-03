package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            outputView.printLotto(lotto.getLottoNumber());
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
        LottoRank lottoRank = new LottoRank(lottos, lastWeekLottoNumber);
        Map<String, Integer> lottoRanks = lottoRank.getLottoRank();
        LottoReturnRate lottoReturnRate = new LottoReturnRate(lottoRanks, getLottoMoney);
        List<LottoRankDto> lottoRankDtos = lottoRanks.entrySet().stream().map(rank -> new LottoRankDto(rank.getKey(), rank.getValue())).toList();
        outputView.printLottoStatistics();
        for(LottoRankDto lottoRankDto : lottoRankDtos){
            outputView.printLottoRank(lottoRankDto.toString());
        }
        outputView.printRateOfReturn(lottoReturnRate.getLottoReturnRate());
    }
}
