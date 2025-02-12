public class Controller {
    private final LottoMarket market;
    private final Statics statics;
    private final Lotto lotto;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(LottoMarket market, Statics statics, Lotto lotto, InputView inputView, OutputView outputView){
        this.market = market;
        this.statics = statics;
        this.lotto = lotto;
        this.inputView = inputView;
        this.outputView = outputView;
    }
}
