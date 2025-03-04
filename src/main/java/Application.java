import controller.LottoController;
import utils.ExceptionHandler;

public class Application {

    public static void main(String[] args) {
        try {
            new LottoController().run();
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
    }

}
