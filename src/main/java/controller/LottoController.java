package controller;

import domain.*;
import domain.value.BonusNumber;
import domain.value.ManualCount;
import domain.value.Money;
import domain.value.WinningNumbers;
import generator.AutoLottoTicketsGenerator;
import generator.ManualLottoTicketsGenerator;
import utils.ParseInputStringToNumbers;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static final int PRICE_PER_TICKET = 1000;

    private final InputView inputView;
    private final OutputView outputView;
    private final AutoLottoTicketsGenerator lottoGenerator;

    public LottoController(InputView inputView, OutputView outputView, AutoLottoTicketsGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money purchaseAmount = getValidPurchaseAmount();
        List<Lotto> purchasedTickets = getValidPurchasedTickets(purchaseAmount);
        WinningNumbers winningNumbers = getValidWinningNumbers();
        BonusNumber bonusNumber = getValidBonusNumber(winningNumbers);

        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();
        LottoPrizeCalculator.Result result =
                lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, purchaseAmount, bonusNumber);

        printResult(result);
    }

    private void printResult(LottoPrizeCalculator.Result result) {
        outputView.printStatusMessage();
        outputView.printResultMessage(3, MatchReward.THREE.getPrize(), result.threeMatchCount);
        outputView.printResultMessage(4, MatchReward.FOUR.getPrize(), result.fourMatchCount);
        outputView.printResultMessage(5, MatchReward.FIVE.getPrize(), result.fiveMatchCount);
        outputView.printBonusResultMessage(5, MatchReward.BONUSFIVE.getPrize(), result.fiveBonusMatchCount);
        outputView.printResultMessage(6, MatchReward.SIX.getPrize(), result.sixMatchCount);
        outputView.printTotalBenefitResultMessage(result.returnRate);
    }

    private BonusNumber getValidBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            outputView.printBonusNumberMessage();
            try {
                BonusNumber bonus = new BonusNumber(inputView.getInputBonusNumber());
                bonus.mustNotDuplicate(winningNumbers.values());
                return bonus;
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                outputView.printErrorMessage(message);
            }
        }
    }

    private List<Lotto> getValidPurchasedTickets(Money purchaseAmount) {
        int total = purchaseAmount.calculateTicketCount(PRICE_PER_TICKET);

        outputView.printManualCountMessage();
        int manual = getValidLottoTicketCount(total);
        int auto = total - manual;

        outputView.printPurchasedTicketsMessage(manual, auto);

        List<Lotto> manualLottoTickets = getValidManualTickets(manual);
        List<Lotto> autoLottoTickets = lottoGenerator.generateAutoLottoTickets(auto);

        List<Lotto> purchasedTickets = new ArrayList<>(manualLottoTickets);
        purchasedTickets.addAll(autoLottoTickets);

        outputView.printLottoNumbers(purchasedTickets);
        return purchasedTickets;
    }

    private int getValidLottoTicketCount(int purchasedTicketCount) {
        while (true) {
            try {
                int manualCount = inputView.getInputManualLottoCount();
                ManualCount manual = new ManualCount(manualCount);
                manual.validateAgainstTotal(purchasedTicketCount);
                return manual.value();
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                outputView.printErrorMessage(message);
            }
        }
    }

    private Money getValidPurchaseAmount() {
        while (true) {
            outputView.printPurchaseMessage();
            try {
                return new Money(inputView.getInputMoney());
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                outputView.printErrorMessage(message);
            }
        }
    }

    private WinningNumbers getValidWinningNumbers() {
        while (true) {
            try {
                outputView.printTargetNumberMessage();
                String rawInput = inputView.getInputTargetNumber();
                return new WinningNumbers(ParseInputStringToNumbers.parse(rawInput));
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                outputView.printErrorMessage(message);
            }
        }
    }

    private List<Lotto> getValidManualTickets(int manualCount) {
        if (manualCount == 0) return List.of();

        while (true) {
            outputView.printManualNumberMessage();
            List<String> lines = inputView.getInputManualLottoNumbers(manualCount);
            try {
                return ManualLottoTicketsGenerator.createManualTickets(lines);
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                outputView.printErrorMessage(message);
            }
        }
    }
}
