import controller.LottoController;
import domain.LottoNumbersGenerator;
import domain.LottoStore;
import service.LottoService;
import view.LottoOutputView;

public class Application {
    public static void main(String[] args) {
        LottoStore lottoStore = new LottoStore(new LottoNumbersGenerator());
        LottoOutputView lottoOutputView = new LottoOutputView();
        LottoService lottoService = new LottoService(lottoStore);
        LottoController lottoController = new LottoController(lottoOutputView, lottoService);

        lottoController.start();
    }
}
