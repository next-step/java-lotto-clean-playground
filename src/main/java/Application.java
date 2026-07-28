import controller.LottoController;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        try {
            new LottoController().run();
        } catch (IllegalArgumentException exception) {
            ResultView.printError(exception.getMessage());
        }
    }
}
