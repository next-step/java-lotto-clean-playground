import java.util.List;
import java.util.Map;

public class Controller {
    private final LottoMarket market;
    private final Statics statics;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(LottoMarket market, Statics statics, InputView inputView, OutputView outputView) {
        this.market = market;
        this.statics = statics;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto() {
        int lottoAmount = inputView.inputLottoAmount();
        int purchasableLotto = lottoAmount / 1000;  // 구매 가능한 로또 개수
        int manualAmount = inputView.manualLottoAmount(purchasableLotto); // 수동 로또 개수 입력받기

        outputView.printInputManualLottoMessage();
        for (int i = 0; i < manualAmount; ++i) {
            market.manualLotto(inputView.inputManualLottoNums());
        }

        // 자동 로또는 전체 개수에서 수동 개수를 뺀 만큼 생성
        int autoLottoCount = purchasableLotto - manualAmount;
        for (int i = 0; i < autoLottoCount; ++i) {
            market.randomLotto();
        }

        // 자동 + 수동 로또 출력
        outputView.printLottos(market.getAllLottos(), manualAmount, autoLottoCount);

        // 당첨 번호 입력 및 설정
        List<Integer> winningNumbers = inputView.intputWinningNums();
        int bonusNumber = inputView.bonusNumber();
        market.setWinningNumbers(winningNumbers, bonusNumber);




        Map<Rank, Long> winningLottos = statics.calcWinningLottos(market.getAllLottos(), market.getWinningNumbers(), bonusNumber);
        outputView.printWinningStatistics(winningLottos);


        double profitRate = statics.calcProfitRate(winningLottos, lottoAmount);
        outputView.printProfitRate(profitRate);
    }
}
