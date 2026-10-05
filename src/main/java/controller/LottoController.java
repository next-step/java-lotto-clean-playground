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
        PurchasePrice purchasePrice = new PurchasePrice(readPurchasedPrice());
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

    private int readPurchasedPrice() {
        while (true) {
            try {
                return parseToInt(inputView.getPurchasePrice());
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto readWinningNumbers() {
        while (true) {
            try {
                return toLotto(inputView.getWinningNumbers());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto readWinningLotto(Lotto winningNumbers) {
        while (true) {
            try {
                LottoNumber bonusNumber = new LottoNumber(parseNumber(inputView.getBonusNumber()));
                return new WinningLotto(winningNumbers, bonusNumber);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private PurchaseCount readPurchaseCount(int totalCount) {
        while (true) {
            try {
                return new PurchaseCount(totalCount, parseToInt(inputView.getManualCount()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto readManualLotto() {
        while (true) {
            try {
                return toLotto(inputView.getManualNumbers());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
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

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("구입 금액은 숫자로 입력되어야 합니다.");
        }
    }

    private Lotto toLotto(String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(this::parseNumber)
                .toList();
        return Lotto.from(numbers);
    }

    private int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }
}
