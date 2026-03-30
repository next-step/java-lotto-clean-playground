package controller;

import domain.Lotto;
import domain.LottoRank;
import domain.Lottos;
import domain.NumbersGenerator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumbersGenerator numbersGenerator;
    private final Validator validator = new Validator();

    public LottoController(InputView inputView, OutputView outputView, NumbersGenerator numbersGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numbersGenerator = numbersGenerator;
    }

    public void run() {
        int purchaseAmount = repeatUntilSuccessAmount();
        Lottos lottos = new Lottos(getnerateLottos(purchaseAmount));

        outputView.printQuantity(lottos.getLottoQuantity());
        outputView.printElements(generateLottoStatus(lottos.getLottos()));

        List<Integer> winnings = repeatUntilSuccessWinningLottos();

        outputView.printStatisticHeader();
        Map<LottoRank, Integer> matchingCount = lottos.calculateMatchCounts(winnings);
        outputView.printElements(generateMatchStatus(matchingCount));

        outputView.printResult(generateProfitResult(matchingCount, purchaseAmount));
    }

    private List<Lotto> getnerateLottos(int purchaseAmount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoList.add(new Lotto(numbersGenerator.generate()));
        }
        return lottoList;
    }

    private <T> T repeatUntilSuccess(Supplier<T> callBack) {
        while (true) {
            try {
                return callBack.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int repeatUntilSuccessAmount() {
        return repeatUntilSuccess(() -> {
            outputView.printStartGuide();
            return validator.validatePriceInput(inputView.readInput());
        });
    }

    private List<Integer> repeatUntilSuccessWinningLottos() {
        return repeatUntilSuccess(() -> {
            outputView.printPrompt();
            return validator.validateLastWinningsInput(inputView.readInput());
        });
    }

    private List<String> generateLottoStatus(List<Lotto> lottoList) {
        return lottoList.stream()
                .map(lotto -> lotto.lottoNumbers().toString())
                .collect(Collectors.toList());
    }

    private List<String> generateMatchStatus(Map<LottoRank, Integer> matchingCounts) {
        return matchingCounts.entrySet().stream()
                .filter(entry -> entry.getKey() != LottoRank.MISS)
                .map(this::formatRankResult)
                .toList();
    }

    private String formatRankResult(Map.Entry<LottoRank, Integer> entry) {
        LottoRank lottoRank = entry.getKey();
        return String.format("%d개 일치 (%d원)- %d개", lottoRank.getMatchCount(), lottoRank.getPrice(), entry.getValue());
    }

    private String generateProfitResult(Map<LottoRank, Integer> matchingCount, int purchaseAmount) {
        long totalProfit = matchingCount.entrySet().stream()
                .mapToLong(entry ->
                        (long) entry.getKey().getPrice() * entry.getValue()
                )
                .sum();
        double rateProfit = (double) totalProfit / (purchaseAmount * 1000);
        String profitResult = String.format("총 수익율은 %.2f입니다.", rateProfit);
        String status = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        if (rateProfit >= 1) {
            status = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        return profitResult + status;
    }
}
