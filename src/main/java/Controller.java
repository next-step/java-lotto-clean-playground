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
        int purchasableLotto = lottoAmount / 1000;
        int manualAmount = inputView.manualLottoAmount(purchasableLotto);

        outputView.printInputManualLottoMessage();
        for (int i = 0; i < manualAmount; ++i) {
            market.manualLotto(inputView.inputManualLottoNums());
        }

        int autoLottoCount = purchasableLotto - manualAmount;
        for (int i = 0; i < autoLottoCount; ++i) {
            market.randomLotto();
        }

        outputView.printLottos(market.getAllLottos(), manualAmount, autoLottoCount);

        List<Integer> winningNumbers = inputView.intputWinningNums();
        int bonusBall = inputView.inputBonusBall();
        market.setWinningNumbers(winningNumbers);

        Map<Rank, Long> winningLottos = statics.calcWinningLottos(market.getAllLottos(), market.getWinningNumbers(), bonusBall);
        outputView.printWinningStatistics(winningLottos);

        double profitRate = statics.calcProfitRate(winningLottos, lottoAmount);
        outputView.printProfitRate(profitRate);
    }
}
