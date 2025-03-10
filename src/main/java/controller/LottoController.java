package controller;

import model.Lotto;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int TICKET_PRICE = 1000;
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();

    public void lottoRun() {
        int purchaseAmount = parsePurchaseAmount(inputView.purchaseAmountTitle());
        validatePurchaseAmount(purchaseAmount);

        int lottoCount = purchaseAmount / TICKET_PRICE;

        List<Lotto> lottoTicketList = generateLottoTickets(lottoCount);
        List<String> lottoTickets = getLottoTicketStrings(lottoTicketList);

        resultView.printLottoResult(lottoTickets);
    }

    private int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(String.valueOf(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구매 금액은 숫자여야 합니다.");
        }
    }

    private void validatePurchaseAmount(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구매 금액은 양수여야 합니다.");
        }
        if (purchaseAmount % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구매 금액은 1000원 단위로 입력되어야 합니다.");
        }
    }

    private List<Lotto> generateLottoTickets(int lottoCount) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new Lotto());
        }
        return lottoTickets;
    }

    private List<String> getLottoTicketStrings(List<Lotto> lottoTickets) {
        return lottoTickets.stream()
                .map(Lotto::toStringLottoTickets)
                .toList();
    }
}
