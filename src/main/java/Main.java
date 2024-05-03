import controller.LottoController;
import service.LottoService;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService());
        lottoController.lottoStart();
    }
}
