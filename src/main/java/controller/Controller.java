package controller;

import domain.Lotto;
import domain.LottoResult;
import domain.LottoTicketGenerator;
import domain.ManualTicketCount;
import domain.Price;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoTicketGenerator lottoTicketGenerator;

    public Controller(InputView inputView, OutputView outputView, LottoTicketGenerator lottoTicketGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoTicketGenerator = lottoTicketGenerator;
    }

    public void run() {
        Price price = inputView.inputPrice();
        ManualTicketCount manualTicketCount = inputView.inputManualTicketCount(price);
        List<List<Integer>> manualTickets = new ArrayList<>();
        if (manualTicketCount.getCount() != 0) {
            manualTickets = inputView.inputManualTickets(manualTicketCount);
        }
        Lotto lotto = new Lotto(price, lottoTicketGenerator, manualTickets);
        outputView.showLottoTickets(lotto, manualTicketCount);

        LottoResult result = lotto.getResults(inputView.inputWinnerTicket(), inputView.inputBonusNumber());
        outputView.showLottoResults(result, result.getProfitRate(price));
    }
}
