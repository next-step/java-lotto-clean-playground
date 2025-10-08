import controller.LottoController;
import generator.AutoLottoTicketsGenerator;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        AutoLottoTicketsGenerator lottoGenerator = new AutoLottoTicketsGenerator();

        LottoController controller = new LottoController(inputView, outputView, lottoGenerator);
        controller.run();
    }
}
