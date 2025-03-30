import controller.LottoController;
import model.LottoNumbersGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoNumbersGenerator());
        lottoController.run();
    }
}
