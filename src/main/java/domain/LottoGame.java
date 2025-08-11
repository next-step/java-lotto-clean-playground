package domain;

import java.util.ArrayList;

import util.LottoInputParser;
import view.LottoInputView;
import view.LottoOutputView;
import vo.Lotto;
import vo.LottoNumber;

public class LottoGame {

    private final LottoInputView inputView;
    private final LottoOutputView outputView;
    private final LottoSimulator lottoSimulator;
    private final LottoResult lottoResult;

    public LottoGame() {
        this.inputView = new LottoInputView();
        this.outputView = new LottoOutputView();
        this.lottoSimulator = new LottoSimulator();
        this.lottoResult = new LottoResult();
    }

    // 로또 게임의 전체실행하는 메서드
    public void start() {
        int money = inputMoney();
        processManualLottos();
        processAutomaticLottos(money);
        processWinningNumbers(money);
    }

    private int inputMoney() {
        return LottoInputParser.parseMoney(inputView.inputMoney());
    }

    private void processManualLottos() {
        int manualLottoCount = Integer.parseInt(inputView.inputManualLottoCount());
        ArrayList<Lotto> manualBuyLotto = LottoInputParser
                .parseManualLottos(inputView.inputManualLottoNumbers(manualLottoCount));
        lottoSimulator.buyManualLotto(manualBuyLotto);
    }

    private void processAutomaticLottos(int money) {
        int manualLottoCount = lottoSimulator.getBuyLottos().size();
        lottoSimulator.buyAutomaticLotto(money - manualLottoCount * 1000);
        outputView.printLottoNumbers(lottoSimulator.getBuyLottos(), manualLottoCount);
    }

    private void processWinningNumbers(int money) {
        Lotto winningNumbers = LottoInputParser.parseWinningNumbers(inputView.inputWinningNumbers());
        LottoNumber bonusBall = LottoInputParser.parseBonusBall(inputView.inputBonusBall());

        lottoResult.calculateResult(lottoSimulator.getBuyLottos(), winningNumbers, bonusBall, money);
        outputView.printWinningStatistics(lottoResult.getWinningStatistics(), lottoResult.calculateProfitRate());
    }
}
