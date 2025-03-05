package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static domain.LottoResult.calculateLottoResult;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Lotto lotto;

    public LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lotto = new Lotto();
    }

    public LottoController(InputView inputView, OutputView outputView, Lotto lotto) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lotto = lotto;
    }

    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();
        int numberOfTickets = lotto.calculateNumberOfTickets(purchaseAmount);

        int manualTicketsCount = inputView.getNumberOfManualTickets(numberOfTickets);
        int autoTicketsCount = numberOfTickets - manualTicketsCount;


        List<LottoTicket> manualTickets = new ArrayList<>();
        if (manualTicketsCount > 0) {
            List<List<LottoNumber>> manualNumbersList = inputView.getManualNumbers(manualTicketsCount);
            for (List<LottoNumber> manualNumbers : manualNumbersList) {
                LottoTicket manualTicket = new LottoTicket(manualNumbers);
                manualTickets.add(manualTicket);
            }
        }

        List<LottoTicket> autoTickets = lotto.generateLottoTickets(autoTicketsCount);

        List<LottoTicket> lottoTickets = new ArrayList<>();
        lottoTickets.addAll(manualTickets);
        lottoTickets.addAll(autoTickets);

        outputView.printPurchaseLotto(manualTicketsCount, autoTicketsCount, lottoTickets);

        List<LottoNumber> winningNumbers = inputView.getWinningNumbers();
        LottoNumber bonusNumber = inputView.getBonusNumber(winningNumbers);

        LottoResult lottoResult = calculateLottoResult(lottoTickets, winningNumbers,bonusNumber);
        outputView.printLottoResult(lottoResult, purchaseAmount);
    }
}
