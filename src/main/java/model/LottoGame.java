package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import view.InputView;
import view.OutputView;

public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;

    private final Integer LOTTO_PRICE = 1_000;

    public LottoGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Integer price = inputView.readPrice();
        Integer totalCount = price / LOTTO_PRICE;
        Integer manualCount = inputView.readManualCount();
        Integer autoCount = totalCount - manualCount;
        List<Lotto> manualLottos = inputView.readManualLottos(manualCount);
        List<Lotto> autoLottos = RandomLottoGenerator.generateLottoNumbers(autoCount);
        outputView.printMyLottos(manualLottos, autoLottos);
        Lotto winningLotto = inputView.readWinningLotto();
        Integer bonusNumber = inputView.readBonusNumber(winningLotto);
        List<Lotto> myLottos = concatMyLottos(manualLottos, autoLottos);
        Map<Rank, Integer> myResult = calculateResult(myLottos, winningLotto, bonusNumber);
        outputView.printResult(myResult);
        Integer profit = computeProfit(myResult);
        outputView.printRateOfReturn(LOTTO_PRICE * totalCount, profit);
    }

    private Integer computeProfit(Map<Rank, Integer> result) {
        return Arrays.stream(Rank.values())
            .filter(it -> it.matchCount() >= 3)
            .mapToInt(it -> result.getOrDefault(it, 0) * it.price())
            .sum();
    }

    private Map<Rank, Integer> calculateResult(List<Lotto> myLottos, Lotto winningLotto, Integer bonusNumber) {
        Map<Rank, Integer> result = new HashMap<>();
        myLottos.stream().forEach(lotto -> {
            Integer count = computeMatchCount(lotto, winningLotto);
            Boolean containsBonusNumber = lotto.numbers().contains(bonusNumber);
            Rank rank = Rank.of(count, containsBonusNumber);
            result.put(rank, result.getOrDefault(rank, 0) + 1);
        });
        return result;
    }

    private Integer computeMatchCount(Lotto myLotto, Lotto winningLotto) {
        return Math.toIntExact(myLotto.numbers().stream()
            .filter(winningLotto.numbers()::contains)
            .count());
    }

    private List<Lotto> concatMyLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        return Stream.concat(
            manualLottos.stream(),
            autoLottos.stream()
        ).toList();
    }
}
