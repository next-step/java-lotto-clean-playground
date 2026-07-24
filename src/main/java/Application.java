import domain.Lotto;
import domain.LotteryStatistics;
import domain.Lottos;
import domain.LottoStore;
import domain.Money;
import view.InputView;
import view.ResultView;

import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final LottoStore lottoStore = new LottoStore();
    private final LotteryStatistics lotteryStatistics = new LotteryStatistics();

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        Money purchasePrice = inputPurchasePrice();
        Lottos lottos = purchaseLottos(purchasePrice);
        Lotto winningLotto = inputWinningLotto();
        int bonusNumber = inputBonusNumber();
        publishStatistics(lottos, winningLotto, bonusNumber, purchasePrice);
    }

    private int inputBonusNumber() {
        while (true) {
            try {
                int bonusNumber = inputView.inputBonusNumber();
                Lotto.validateNumberRange(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Money inputPurchasePrice() {
        while (true) {
            try {
                return new Money(inputView.inputPrice());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lottos purchaseLottos(Money purchasePrice) {
        Lottos lottos = lottoStore.buy(purchasePrice);
        resultView.printLottos(lottos);
        return lottos;
    }

    private Lotto inputWinningLotto() {
        while (true) {
            try {
                String input = inputView.inputWinningLotto();
                List<Integer> numbers = parseLottoNumbers(input.split(","));
                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Integer> parseLottoNumbers(String[] numbers) {
        try {
            return Arrays.stream(numbers)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자만 입력해주세요.");
        }
    }

    private void publishStatistics(Lottos lottos, Lotto winningLotto, int bonusNumber, Money purchasePrice) {
        lotteryStatistics.calculateStatistics(lottos, winningLotto, bonusNumber);
        Money totalPrize = lotteryStatistics.calculatePrize();
        resultView.printStatistics(lotteryStatistics, totalPrize, purchasePrice);
    }
}
