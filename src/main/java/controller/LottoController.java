package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.LottoWinningResult;
import dto.LottoStatus;
import util.NumbersGenerator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumbersGenerator<LottoNumber> numbersGenerator;
    private final Validator validator;

    public LottoController(InputView inputView, OutputView outputView, NumbersGenerator<LottoNumber> numbersGenerator, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numbersGenerator = numbersGenerator;
        this.validator = validator;
    }

    public void run() {
        Lottos lottos = buyLottos();

        List<LottoNumber> winningLotto = readWinningLotto();

        showResultStatistics(lottos, winningLotto);
    }

    private Lottos buyLottos() {
        int purchaseAmount = readAmount();
        Lottos lottos = new Lottos(getnerateLottos(purchaseAmount));

        outputView.printQuantity(lottos.getQuantity());
        outputView.printElements(lottos.toStatus().stream()
                .map(LottoStatus::toString)
                .toList());
        return lottos;
    }

    private void showResultStatistics(Lottos lottos, List<LottoNumber> winningLotto) {
        outputView.printStatisticHeader();
        LottoWinningResult winningResult = new LottoWinningResult(lottos.calculateMatchCounts(winningLotto));
        winningResult.getLottoStatistics().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> outputView.printStatistics(
                        entry.getKey().getMatchCount(),
                        entry.getKey().getPrice(),
                        entry.getValue()
                ));
        outputView.printResult(winningResult.getLottoProfitRate(lottos.getQuantity()));
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

    private int readAmount() {
        return repeatUntilSuccess(() -> {
            outputView.printStartGuide();
            return validator.validatePriceInput(inputView.readInput());
        });
    }

    private List<LottoNumber> readWinningLotto() {
        return repeatUntilSuccess(() -> {
            outputView.printPrompt();
            return validator.validateLastWinningsInput(inputView.readInput());
        });
    }

    private List<Lotto> getnerateLottos(int purchaseAmount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoList.add(new Lotto(numbersGenerator.generate()));
        }
        return lottoList;
    }
}
