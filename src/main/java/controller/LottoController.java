package controller;

import model.Lotto;
import model.LottoTickets;
import view.InputView;
import view.ResultView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        try {
            int purchaseAmount = InputView.getPurchaseAmount();

            validatePurchaseAmount(purchaseAmount);

            int ticketCount = Lotto.getTicketCount(purchaseAmount);

            LottoTickets lottoTickets = new LottoTickets(ticketCount);

            ResultView.printOrderTickets(ticketCount);
            ResultView.printPurchasedLottoTickets(formatTickets(lottoTickets.getTickets()));
        }catch (IllegalArgumentException e){
            ResultView.printErrorMessage(e.getMessage());
        }
    }

    private List<String> formatTickets(List<Lotto> tickets) {
        return tickets.stream()
                .map(this::convertLottoToString)
                .toList();
    }

    private String convertLottoToString(Lotto lotto) {
        return lotto.getSortedNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < Lotto.LOTTO_PRICE) {
            throw new IllegalArgumentException("구매 금액은 1000원 이상이어야 합니다.");
        }

        if (purchaseAmount % Lotto.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위여야 합니다.");
        }
    }

}
