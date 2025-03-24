import controller.LottoController;
import model.LottoNumberGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoNumberGenerator());
        lottoController.run();
    }
}
