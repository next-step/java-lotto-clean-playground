package controller;

import domain.TicketLotto;
import view.InputView;

public class Main {

    public int Main() {
        InputView inputView = new InputView();
        TicketLotto ticketLotto = new TicketLotto();

        int money = inputView.getMoney();
        return ticketLotto.getCountLotto(money);
    }
}
