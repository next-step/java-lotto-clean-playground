import domain.lotto.LottoService;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService(inputView, outputView);
        lottoService.run();
    }
}
