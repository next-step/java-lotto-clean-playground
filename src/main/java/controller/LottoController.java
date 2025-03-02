package controller;

import model.*;
import service.LottoService;
import view.InputView;
import view.ResultView;

import java.util.Arrays;
import java.util.List;

public class LottoController {

    private static final int TICKET_PRICE = 1000;
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoService lottoService = new LottoService();

    public void lottoRun() {
        int purchaseAmount = getPurchaseAmount();
        int manualCount = inputView.inputManualCount();
        List<String> manualLottoList = getManualLottoList(manualCount);

        List<Lotto> lottoList = lottoService.generateLottos(purchaseAmount, manualLottoList);
        resultView.displayPurchasedLottoTickets(getLottoTicketStrings(lottoList), manualCount);

        LottoResult lottoResult = new LottoResult(getWinningNumbers(), inputView.inputBonus());
        List<LottoRank> lottoRanks = lottoService.calculateRank(lottoResult, lottoList);
        printResults(lottoRanks);
    }

    private void printResults(List<LottoRank> lottoRanks) {
        List<String> lottoRankStrings = lottoService.convertLottoRanksToStrings(lottoRanks);
        resultView.printLottoStatistics(lottoRankStrings);
        resultView.printEarningsRate(lottoService.calculateEarningsRate(lottoRanks));
    }

    private int getPurchaseAmount() {
        int amount = parsePurchaseAmount(inputView.inputPurchaseAmount());
        validatePurchaseAmount(amount);
        return amount;
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

    private List<String> getManualLottoList(int manualCount) {
        return inputView.inputManualCountList(manualCount);
    }

    private List<String> getLottoTicketStrings(List<Lotto> lottoTickets) {
        return lottoTickets.stream()
                .map(Lotto::toStringLottoTickets)
                .toList();
    }

    private List<Integer> getWinningNumbers() {
        String winningLottoNumbers = inputView.inputWinningNumbers();
        return convertWinningNumbers(winningLottoNumbers);
    }

    private List<Integer> convertWinningNumbers(String winningLottoNumbers) {
        return Arrays.stream(winningLottoNumbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
