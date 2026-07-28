import controller.LottoController;
import view.InputView;
import view.ResultView;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        ResultView resultView = new ResultView(System.out);
        LottoController lottoController = new LottoController(inputView, resultView);

        try {
            lottoController.run();
        } catch (IllegalArgumentException exception) {
            resultView.printError(exception.getMessage());
        }
    }
}
