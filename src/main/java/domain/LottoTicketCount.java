package domain;

public class LottoTicketCount {
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;
    private int lottoTicketCount;

    public int convertLottoPriceToTicketCount(int totalLottoPrice) {
        lottoTicketCount = totalLottoPrice / PRICE_PER_ONE_LOTTO_TICKET;
        return lottoTicketCount;
    }

}
