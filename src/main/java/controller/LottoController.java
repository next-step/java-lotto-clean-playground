package controller;

import model.*;
import view.InputView;
import view.ResultView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static model.PurchaseValidator.validateLottoInputName;

public class LottoController {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoMachine lottoMachine = new LottoMachine();

    public void lottoRun() {
        int purchaseAmount = validateLottoInputName(inputView.inputPurchaseAmount());
        int manualCount = inputView.inputManualCount();
        List<String> manualLottos = inputView.inputManualLottoNumbers(manualCount);

        List<Lotto> lottos = lottoMachine.purchaseTickets(purchaseAmount, convertToLottos(manualLottos));
        resultView.displayPurchasedLottoTickets(formatLottoTickets(lottos), manualCount);

        LottoResult lottoResult = createLottoResult();
        List<LottoRank> lottoRanks = lottoResult.calculateRank(lottos);
        printResults(lottoRanks, lottoResult);
    }

    private void printResults(List<LottoRank> lottoRanks, LottoResult lottoResult) {
        List<String> lottoRankStrings = convertLottoRanksToStrings(lottoRanks);
        resultView.printLottoStatistics(lottoRankStrings);
        resultView.printEarningsRate(lottoResult.calculateEarningsRate(lottoRanks));
    }

    private List<String> convertLottoRanksToStrings(List<LottoRank> lottoRanks) {
        List<String> lottoRankStrings = new ArrayList<>();
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NO_WINNER) continue;

            long count = getRankCount(lottoRanks, rank);
            String rankString = generateRankString(rank, count);
            lottoRankStrings.add(rankString);
        }
        return lottoRankStrings;
    }

    private List<String> formatLottoTickets(List<Lotto> lottoTickets) {
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

    private LottoResult createLottoResult() {
        List<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = inputView.inputBonus();
        return new LottoResult(winningNumbers, bonusNumber);
    }

    private List<Lotto> convertToLottos(List<String> manualLottos) {
        return manualLottos.stream()
                .map(this::convertToSingleLotto)
                .toList();
    }

    private Lotto convertToSingleLotto(String lottoNumbersString) {
        List<Integer> lottoNumbers = Arrays.stream(lottoNumbersString.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
        return new Lotto(lottoNumbers);
    }

    private long getRankCount(List<LottoRank> lottoRanks, LottoRank rank) {
        return lottoRanks.stream()
                .filter(lottoRank -> lottoRank == rank)
                .count();
    }

    private String generateRankString(LottoRank rank, long count) {
        if (rank == LottoRank.FIVE_MATCHES_BONUS) {
            return rank.getMatchCount() + "개, 보너스 볼 일치 (" + rank.getPrice() + "원) - " + count + "개";
        }
        return rank.getMatchCount() + "개 일치 (" + rank.getPrice() + "원) - " + count + "개";
    }
}
