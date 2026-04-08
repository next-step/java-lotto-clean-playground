package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.strategy.NumbersGenerator;
import dto.LottoStatistics;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
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

        Lotto winningLotto = readWinningLotto();
        LottoNumber bonusNumber = readBonusNumber(winningLotto);

        showResultStatistics(lottos, winningLotto, bonusNumber);
    }

    private Lottos buyLottos() {
        int totalAmount = readTotalLottoAmount();
        int manualAmount = readManualLottoAmount(totalAmount);
        int autoAmount = totalAmount - manualAmount;

        Lottos lottos = generateLottos(manualAmount, autoAmount);

        outputView.printQuantity(manualAmount, autoAmount);
        outputView.printElements(lottos.toStatus());
        return lottos;
    }

    private Lottos generateLottos(int manualAmount, int autoAmount) {
        List<Lotto> totalLottos = new ArrayList<>();

        if (manualAmount != 0) {
            outputView.printInputManualNumbers();
            List<Lotto> manualLottos = generateManualLottos(manualAmount);
            totalLottos.addAll(manualLottos);
        }
        List<Lotto> autoLottos = generateAutoLottos(autoAmount);
        totalLottos.addAll(autoLottos);

        return new Lottos(totalLottos);
    }

    private List<Lotto> generateManualLottos(int manualCount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            lottoList.add(readLotto());
        }
        return lottoList;
    }

    private List<Lotto> generateAutoLottos(int purchaseAmount) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < purchaseAmount; i++) {
            lottoList.add(new Lotto(numbersGenerator.generate()));
        }
        return lottoList;
    }

    private void showResultStatistics(Lottos lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        outputView.printStatisticHeader();
        LottoStatistics lottoStatistics = lottos.getLottoStatistics(winningLotto, bonusNumber);
        outputView.printStatistics(lottoStatistics);

        outputView.printResult(lottoStatistics.profitRate());
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

    private int readTotalLottoAmount() {
        return repeatUntilSuccess(() -> {
            outputView.printInputStartGuide();
            return validator.validatePriceInput(inputView.readInput());
        });
    }

    private int readManualLottoAmount(int purchaseAmount) {
        return repeatUntilSuccess(() -> {
            outputView.printInputManualCount();
            return validator.validateManualInput(inputView.readInput(), purchaseAmount);
        });
    }

    private Lotto readWinningLotto() {
        return repeatUntilSuccess(() -> {
            outputView.printPrompt();
            return new Lotto(validator.validateLottoInput(inputView.readInput()));
        });
    }

    private Lotto readLotto() {
        return repeatUntilSuccess(() -> new Lotto(validator.validateLottoInput(inputView.readInput())));
    }

    private LottoNumber readBonusNumber(Lotto winningLotto) {
        return repeatUntilSuccess(() -> {
            outputView.printBonusPrompt();
            return validator.validateBonusNumberInput(winningLotto, inputView.readInput());
        });
    }
}
