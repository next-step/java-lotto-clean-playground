package controller;

import dto.LottoResultDto;
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
        List<LottoResultDto> lottoResultDtos = convertToLottoResult(lottoRanks);
        resultView.printLottoStatistics(lottoResultDtos);
        resultView.printEarningsRate(lottoResult.calculateEarningsRate(lottoRanks));
    }

    private List<LottoResultDto> convertToLottoResult(List<LottoRank> lottoRanks) {
        List<LottoResultDto> lottoResultDtos = new ArrayList<>();
        for(LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NO_WINNER) continue;
            long count = getRankCount(lottoRanks, rank);
            lottoResultDtos.add(new LottoResultDto(rank, count));
        }
        return lottoResultDtos;
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
}
