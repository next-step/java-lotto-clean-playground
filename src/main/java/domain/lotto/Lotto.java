package domain.lotto;

public class Lotto {
    private int lottoMoney;
    private int lottoTicketCount;

    public Lotto(int lottoMoney,
                 int lottoTicketCount) {
        this.lottoMoney = lottoMoney;
        this.lottoTicketCount = lottoTicketCount;
    }

    public int getLottoTicketCount() {
        return this.lottoTicketCount;
    }

    public int getLottoMoney() {
        return this.lottoMoney;
    }
}
