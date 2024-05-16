package fakeTest;

import domain.TicketLotto;
import view.InputView;
import view.OutputView;
import java.util.List;

public class FakeLottoController {

    private static final int INITIAL_NUM = 0;

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        TicketLotto ticketLotto = new TicketLotto();
        FakeGenerateRandomNumber generateRandomNumber = new FakeGenerateRandomNumber();

        int money = inputView.getMoney();
        int paper = ticketLotto.getCountLotto(money);

        for (int i = INITIAL_NUM; i < paper; i++) {
            List<Integer> lottoList = generateRandomNumber.generateNumber();
            outputView.lotto(lottoList);
        }
    }
}