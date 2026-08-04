package domain;

public class LottoTicketCount {
    public static final int PRICE_PER_ONE_LOTTO_TICKET = 1000;
    private int lottoTicketCount;

    public int convertLottoPriceToTicketCount(int totalLottoPrice) {
        validatePurchaseAmount(totalLottoPrice);
        lottoTicketCount = totalLottoPrice / PRICE_PER_ONE_LOTTO_TICKET;
        return lottoTicketCount;
    }

    private void validatePurchaseAmount(int price) {
        if (price < PRICE_PER_ONE_LOTTO_TICKET) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_ONE_LOTTO_TICKET + "원 이상이어야 합니다.");
        }
        if (price % PRICE_PER_ONE_LOTTO_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 " + PRICE_PER_ONE_LOTTO_TICKET + "원 단위로 입력해야 합니다.");
        }
    }
}
