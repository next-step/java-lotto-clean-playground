package domain;

public class LottoTicketCount { //로또 금액 들어오면 비즈니스 규칙에 알맞게 수량을 정수로 반환해줌
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;
    int lottoTicketCount;

    public int convertLottoPriceToTickets(int totalLottoPrice) {
        lottoTicketCount = totalLottoPrice / PRICE_PER_ONE_LOTTO_TICKET;

        return lottoTicketCount;
    }


}
