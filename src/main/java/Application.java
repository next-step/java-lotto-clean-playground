import controller.LottoController;
import service.LottoService;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService());
        lottoController.lottoManualStart();
    }
}
