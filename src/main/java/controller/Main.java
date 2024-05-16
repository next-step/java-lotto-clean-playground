package controller;

import domain.LottoTicket;
import view.InputView;

public class Main {

    public int Main() {
        InputView inputView = new InputView();
        LottoTicket lottoTicket = new LottoTicket();

        int money = inputView.getMoney();
        return lottoTicket.getCountLotto(money);
    }
}
