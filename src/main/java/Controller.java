import java.util.List;
import java.util.Map;

public class Controller {
    private final LottoMarket market;
    private final Statics statics;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(LottoMarket market, Statics statics, InputView inputView, OutputView outputView){
        this.market = market;
        this.statics = statics;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startLotto(){
        int lottoAmount = inputView.inputLottoAmount();
        List<Integer> winingNumbers = inputView.intputWinningNums();
        market.lottoMarketSet(winingNumbers);

        for(int i=0; i<lottoAmount/1000; ++i){
            market.randomLotto();
        }

        outputView.printLottos(market.getLottos());
        Map<Integer, Long> winingLottos = statics.calcWiningLottos(market.getLottos(), winingNumbers);
        int profitRate = (int) statics.calcProfitRate(winingLottos, lottoAmount);

        outputView.printProfitRate(profitRate);
    }

}
