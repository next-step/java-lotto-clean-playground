import domain.Lotto;
import domain.LottoCalculator;
import domain.LottoMachine;
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
        final int amount = inputView.getMoney(); //돈 받기
        Money money = new Money(amount); // 돈 저장
        final int number = LottoMachine.calculateTicketCount(money); // 로또 뽑는 횟수
        resultView.printPurchaseCount(number);
        Lottos lottos = purchaseLotto(number);
        Lotto winnerNumbers = inputView.getWinnerNumbers(); //당첨번호 로또 입력
        LottoCalculator calculator = calculatorResult(lottos, winnerNumbers); //당첨 결과 계산
        printStatistics(calculator, money);//최종 통계 및 수익률 출력
    }


    public Lottos purchaseLotto(int count) {
        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = random.generate();
            printLotto(lotto);
            purchased.add(lotto);
        }
        return new Lottos(purchased);
    }

    public void printLotto(Lotto lotto) {
        resultView.printLottoNumbers(lotto);
    }

    private LottoCalculator calculatorResult(Lottos lottos, Lotto winnerNumbers) {
        LottoCalculator calculator = new LottoCalculator();
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.getMatchNumbers(winnerNumbers);
            Rank rank = Rank.MISS.valueOf(matchCount);
            calculator.valueAdd(rank);
        }
        return calculator;
    }

    public void printStatistics(LottoCalculator calculator, Money money) {
        resultView.printStatics();
        for (Rank rank : Rank.values()) {
            AddWinningMoney(calculator, rank);
        }
        double yield = calculator.calculateYield(money);
        resultView.printYield(yield, yield >= 1.0);
    }

    public void AddWinningMoney(LottoCalculator calculator, Rank rank) {
        if (rank != Rank.MISS) {
            resultView.printWinningStatics(
                    rank.getMatchnumbers(),
                    rank.getPrizemoney(),
                    calculator.getResult().get(rank)
            );
        }
    }

    public static void main(String[] args) {
        Application lotto = new Application();
        lotto.run();

    }

}
