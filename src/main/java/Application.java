import domain.Lotto;
import domain.LottoCalculator;
import domain.LottoMachine;
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
        final int amount = inputView.getMoney(); // 돈 받기
        Money money = new Money(amount); // 돈 저장
        final int count = LottoMachine.calculateTicketCount(money); // 로또 뽑는 횟수
        final int manualCount = inputView.getManualCount(count); // 수동 뽑기 횟수 받기
        final int randomCount = count - manualCount;

        Lottos manualLottos = purchaseManualLottos(manualCount);
        Lottos randomLottos = purchaseLotto(randomCount);
        Lottos purchasedLottos = Lottos.merge(manualLottos, randomLottos);

        resultView.printPurchaseCount(manualCount, randomCount); // 횟수 출력
        printAllLottos(purchasedLottos); // 합쳐진 모든 로또 출력

        Lotto winnerNumbers = inputView.getWinnerNumbers(); // 당첨번호 로또 입력
        LottoNumber bonusNumber = inputView.getBonusNumber(winnerNumbers); // 보너스 볼 입력

        LottoCalculator calculator = new LottoCalculator();
        purchasedLottos.calculateResults(winnerNumbers, bonusNumber, calculator); // 결과 계산
        printStatistics(calculator, money); // 최종 통계 및 수익률 출력
    }

    // 모든 로또를 출력하기 위한 메서드
    private void printAllLottos(Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getLottos()) {
            resultView.printLottoNumbers(lotto);
        }
    }

    public Lottos purchaseLotto(int count) {
        List<Lotto> purchased = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = random.generate();
            purchased.add(lotto);
        }
        return new Lottos(purchased);
    }

    public void printLotto(Lotto lotto) {
        resultView.printLottoNumbers(lotto);
    }

    public void printStatistics(LottoCalculator calculator, Money money) {
        resultView.printStatics();
        for (Rank rank : Rank.values()) {
            AddWinningMoney(calculator, rank);
        }
        double yield = calculator.calculateYield(money);
        resultView.printYield(yield);
    }

    public void AddWinningMoney(LottoCalculator calculator, Rank rank) {
        if (rank != Rank.MISS) {
            resultView.printWinningStatics(
                    rank.getMatchCount(),
                    rank.getPrizeMoney(),
                    calculator.getResult().get(rank),
                    rank.getMatchBonus()
            );
        }
    }

    public Lottos purchaseManualLottos(int manualCount) {
        if (manualCount == 0) {
            return new Lottos(new ArrayList<>());
        }

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();

        while (manualLottos.size() < manualCount) {
            try {
                manualLottos.add(inputView.getManualLotto());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return new Lottos(manualLottos);
    }

    public static void main(String[] args) {
        Application lotto = new Application();
        lotto.run();
    }

}
