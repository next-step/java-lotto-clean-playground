package domain;

public class MoneyToTicket {
    public static LottoTicketCount MoneyToTicket(Money money) {
        return new LottoTicketCount(money.getMoney() / 1000);
    }
}
