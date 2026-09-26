package controller;

import domain.NumberGenerator;
import domain.RandomNumberGenerator;
import domain.purchase.Lotto;
import domain.purchase.Lottos;
import domain.purchase.PurchasePrice;
import domain.winning.LottoResult;
import domain.winning.RateOfReturn;
import domain.winning.WinningLotto;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        PurchasePrice purchasePrice = new PurchasePrice(readPurchasedPrice());
        Lottos lottos = createLottos(purchasePrice.calculateLottoCount());
        outputView.printLottos(lottos);

        Lotto winningNumbers = readWinningNumbers();
        LottoResult result = new WinningLotto(winningNumbers).match(lottos);
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

    private Lottos createLottos(int count) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
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
        String[] tokens = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(parseNumber(token.trim()));
        }
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
