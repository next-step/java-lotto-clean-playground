import domain.LottoGenerator;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        LottoGenerator generator = new LottoGenerator();
        new LottoController(inputView, outputView, generator).run();
    }
}
