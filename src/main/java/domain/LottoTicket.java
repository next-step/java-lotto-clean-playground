package domain;


public class LottoTicket {

    private static final int TICKET_PRICE = 1000;

    public int getCountLotto(int money) {
        return countLotto(money);
    }

    private int countLotto(int money) {
        return money / TICKET_PRICE;
    }
}
