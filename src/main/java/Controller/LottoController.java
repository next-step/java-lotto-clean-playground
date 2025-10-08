package Controller;

import Model.*;
import View.InputView;
import View.OutView;

import java.util.List;

public class LottoController {
    public void startLotto() {
        InputView inputView = new InputView();
        OutView outView = new OutView();


        int money = getValidMoney(inputView, outView);

        outView.getManualLottoCountHelp();
        int manualLottoCount = inputView.getManualLottoCount();

        outView.promptForManualLotto();
        Lottos lottos = new Lottos();

        inputView.resetBuffer();
        for (int i = 0; i < manualLottoCount; i++) {
            Lotto validManualLotto = getValidManualLotto(inputView);
            lottos.addLotto(validManualLotto);
        }

        int autoLottoCount = lottos.getAutoLottoCount(money, manualLottoCount);
        lottos.makeAutoLotto(autoLottoCount);

        outView.printLottosStaus(autoLottoCount, manualLottoCount);

        outView.printLottos(lottos);

        outView.promptForJackpot();
        String[] winningNumber = inputView.getJackpotNumber();

        outView.promptForBonus();
        int bonusNumber = inputView.getBonus();

        try {
            Jackpot jackpot = new Jackpot(winningNumber, bonusNumber);

            LottoMatcher lottoMatcher = new LottoMatcher(lottos, jackpot);

            outView.printLottoMatcher(lottoMatcher.getMatchCounts(), lottoMatcher.getRate(money));
        } catch (IllegalArgumentException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }

    }

    private int getValidMoney(InputView inputView, OutView outView) {
        while (true) {
            try {
                outView.getMoneyHelp();
                int money = inputView.getMoney();
                validate(money);
                return money;
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto getValidManualLotto(InputView inputView){
        while(true){
            try{
                return new ManualLotto(inputView.manualLotto());
            } catch (IllegalArgumentException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private void validate(int money) {
        if (money < 0)
            throw new IllegalArgumentException("금액이 0보다 작을 순 없습니다!");
        if (money % 1000 != 0)
            throw new IllegalArgumentException("1000원 단위로 입력해주세요!");
    }
}
