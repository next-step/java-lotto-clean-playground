package controller;

import model.Lotto;
import view.InputView;
import view.ResultView;
import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final int TICKET_PRICE = 1000;

    public void lottoRun() {
        int purchaseAmount = InputView.purchaseAmountTitle();

        int lottoCount = purchaseAmount / TICKET_PRICE;

        List<Lotto> lottoTickets = generateLottoTickets(lottoCount);

        ResultView.printLottoResult(lottoTickets);

        InputView.closeScanner();
    }

    private static List<Lotto> generateLottoTickets(int lottoCount) {
        List<Lotto> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoTickets.add(new Lotto());
        }
        return lottoTickets;
    }
}
