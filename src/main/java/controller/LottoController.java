package controller;

import domain.generation.LottoFactory;
import domain.lotto.Lotto;
import domain.lotto.Lottos;
import domain.lotto.WinningLotto;
import domain.purchase.PurchaseCount;
import domain.purchase.PurchasePrice;
import domain.result.LottoResult;
import dto.ResultDto;

import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoFactory lottoFactory;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoFactory lottoFactory
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoFactory = lottoFactory;
    }

    public void run() {
        try {
            runApp();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void runApp() {
        PurchasePrice purchasePrice = createPurchasePrice();
        PurchaseCount purchaseCount = createPurchaseCount(purchasePrice);

        Lottos lottos = createLottos(purchaseCount);
        outputView.printLottos(lottos, purchaseCount);

        WinningLotto winningLotto = createWinningLotto();

        ResultDto resultDto = createResult(lottos, winningLotto, purchasePrice);
        outputView.printResult(resultDto);
    }

    private PurchasePrice createPurchasePrice() {
        return new PurchasePrice(inputView.getPurchasePrice());
    }

    private PurchaseCount createPurchaseCount(PurchasePrice purchasePrice) {
        int totalCount = purchasePrice.calculateLottoCount();
        int manualCount = inputView.getManualCount();
        return new PurchaseCount(totalCount, manualCount);
    }

    private Lottos createLottos(PurchaseCount purchaseCount) {
        Lottos manualLottos = createManualLottos(purchaseCount.getManualCount());
        Lottos autoLottos = lottoFactory.create(purchaseCount.getAutoCount());

        return manualLottos.combine(autoLottos);
    }

    private Lottos createManualLottos(int manualCount) {
        List<Lotto> manualLottos = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            List<Integer> numbers = inputView.getManualLottoNumbers();
            manualLottos.add(Lotto.from(numbers));
        }

        return new Lottos(manualLottos);
    }

    private WinningLotto createWinningLotto() {
        List<Integer> winningNumbers = inputView.getWinningNumbers();
        int bonusNumber = inputView.getBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private ResultDto createResult(
            Lottos lottos,
            WinningLotto winningLotto,
            PurchasePrice purchasePrice
    ) {
        LottoResult result = lottos.calculateResult(winningLotto);
        return createResultDto(result, purchasePrice);
    }

    private ResultDto createResultDto(LottoResult result, PurchasePrice purchasePrice) {
        return new ResultDto(
                result.getResults(),
                result.calculateRateOfReturn(purchasePrice)
        );
    }
}
