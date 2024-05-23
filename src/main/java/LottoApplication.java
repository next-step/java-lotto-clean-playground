import controller.LottoController;
import model.dice.LottoDice;
import view.input.ConsoleInputView;
import view.output.ConsoleOutputView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new LottoDice()
        );
        
        lottoController.play();
    }
}
