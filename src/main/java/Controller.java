import java.util.List;
import java.util.Map;

public class Controller {
    private final Statics statics;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(Statics statics, InputView inputView, OutputView outputView) {
        this.statics = statics;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto() {
        int lottoAmount = inputView.inputLottoAmount();
        LottoMarket market = new LottoMarket(generateWinningNumbers()); // 당첨 번호를 생성자에서 설정

        for (int i = 0; i < lottoAmount / 1000; ++i) {
            market.randomLotto();
        }

        outputView.printLottos(market.getLottos());

        Map<Integer, Long> winingLottos = statics.calcWiningLottos(market.getLottos(), market.getWiningNumbers());

        outputView.printWinningStatistics(winingLottos);

        double profitRate = statics.calcProfitRate(winingLottos, lottoAmount);

        outputView.printProfitRate(profitRate);
    }

    private List<Integer> generateWinningNumbers() {
        return inputView.intputWinningNums();
    }
}
