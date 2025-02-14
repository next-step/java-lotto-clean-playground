public class Main {
    public static void main(String[] args) {
        Statics statics = new Statics();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Controller controller = new Controller(statics, inputView, outputView);
        controller.startLotto();
    }
}
