public class Main {
    public static void main(String[] args) {
        LottoMarket market = new LottoMarket();
        Statics statics = new Statics();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(market, statics, inputView, outputView);
        controller.run();
    }

}
