import domain.Lotto;
import domain.LottoCalculator;
import domain.LottoNumber;
import domain.Lottos;
import domain.Money;
import domain.RandomLottoGenerator;
import domain.Rank;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.ResultView;

public class Application {
    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();
    private final RandomLottoGenerator random = new RandomLottoGenerator();

    public void run() {
        Money money = getValidMoney();
        final int number = money.getNumber();

        resultView.printPurchaseCount(number);
        Lottos lottos = purchaseLotto(number);

        Lotto winnerNumbers = getValidWinnerNumbers();

        LottoCalculator calculator = lottos.matchAll(winnerNumbers);

        printStatistics(calculator, money);
    }

    private Money getValidMoney() {
        while (true) {
            try {
                return new Money(inputView.getMoney());
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto getValidWinnerNumbers() {
        while (true) {
            try {
                String input = inputView.getWinnerNumbers();
                return parseToLotto(input);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto parseToLotto(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어 있습니다.");
        }
        String[] tokens = input.split(",");
        List<LottoNumber> winningNumbers = new ArrayList<>();
        try {
            for (String token : tokens) {
                winningNumbers.add(new LottoNumber(Integer.parseInt(token.trim())));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다.");
        }
        return new Lotto(winningNumbers);
    }

    public Lottos purchaseLotto(int count) {
        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = random.generate();
            printLottoAdapter(lotto);
            purchased.add(lotto);
        }
        return new Lottos(purchased);
    }

    private void printLottoAdapter(Lotto lotto) {
        List<Integer> rawNumbers = new ArrayList<>();
        for (LottoNumber num : lotto.getNumbers()) {
            rawNumbers.add(num.getNumber());
        }
        resultView.printLottoNumbers(rawNumbers);
    }

    public void printStatistics(LottoCalculator calculator, Money money) {
        resultView.printStatics();
        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) {
                resultView.printWinningStatics(
                        rank.getMatchnumbers(),
                        rank.getPrizemoney(),
                        calculator.getResult().get(rank)
                );
            }
        }
        double yield = calculator.calculateYield(money);
        resultView.printYield(yield, yield >= 1.0);
    }

    public static void main(String[] args) {
        Application lotto = new Application();
        lotto.run();
    }
}