import controller.LottoController;
import view.input.ConsoleInputView;
import view.output.ConsoleOutputView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new ConsoleInputView(),
                new ConsoleOutputView()
        );
        
        lottoController.play();
    }
}
