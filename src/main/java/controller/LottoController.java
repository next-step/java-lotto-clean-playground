package controller;

import domain.NumberGenerator;
import domain.purchase.Lotto;
import domain.purchase.LottoNumber;
import domain.purchase.Lottos;
import domain.purchase.PurchaseCount;
import domain.purchase.PurchasePrice;
import domain.winning.LottoResult;
import domain.winning.RateOfReturn;
import domain.winning.WinningLotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }
    public void run() {
        PurchasePrice purchasePrice = readPurchasePrice();
        PurchaseCount purchaseCount = readPurchaseCount(purchasePrice.calculateLottoCount());
        Lottos lottos = createLottos(purchaseCount);
        outputView.printLottos(purchaseCount.getManualCount(), purchaseCount.getAutoCount(), lottos);

        Lotto winningNumbers = readWinningNumbers();
        WinningLotto winningLotto = readWinningLotto(winningNumbers);
        LottoResult result = winningLotto.match(lottos);
        outputView.printResult(result);

        RateOfReturn rateOfReturn = new RateOfReturn(result.calculateTotalPrize(), purchasePrice);
        outputView.printRateOfReturn(rateOfReturn.getValue());
    }

    private <T> T retry(Supplier<T> supplier) {
        try{
            return supplier.get();
        } catch(Exception e){
            System.out.println(e.getMessage());
            return retry(supplier);
        }
    }

    private PurchasePrice readPurchasePrice() {
        return retry(() -> new PurchasePrice(
                parseNumber(inputView.getPurchasePrice(), "구입 금액은 숫자여야 합니다.")));
    }

    private PurchaseCount readPurchaseCount(int totalCount) {
        return retry(() -> new PurchaseCount(totalCount,
                parseNumber(inputView.getManualCount(), "수동 구매 개수는 숫자여야 합니다.")));
    }

    private Lotto readManualLotto() {
        return retry(() -> toLotto(inputView.getManualNumbers(), "로또 번호는 숫자여야 합니다."));
    }

    private Lotto readWinningNumbers() {
        return retry(() -> toLotto(inputView.getWinningNumbers(), "당첨 번호는 숫자여야 합니다."));
    }

    private WinningLotto readWinningLotto(Lotto winningNumbers) {
        return retry(() -> new WinningLotto(winningNumbers, new LottoNumber(
                parseNumber(inputView.getBonusNumber(), "보너스 번호는 숫자여야 합니다."))));
    }

    private Lottos createLottos(PurchaseCount purchaseCount) {
        List<Lotto> lottoList = new ArrayList<>();
        inputView.printManualNumbersGuide();
        for (int i = 0; i < purchaseCount.getManualCount(); i++) {
            lottoList.add(readManualLotto());
        }
        for (int i = 0; i < purchaseCount.getAutoCount(); i++) {
            lottoList.add(new Lotto(numberGenerator.generate()));
        }
        return new Lottos(lottoList);
    }

    private Lotto toLotto(String input, String errorMessage) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(token -> parseNumber(token, errorMessage))
                .toList();
        return Lotto.from(numbers);
    }

    private int parseNumber(String input, String errorMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
