import controller.LottoController;
import domain.generation.LottoFactory;
import domain.generation.NumberGenerator;
import domain.generation.RandomNumberGenerator;
import view.InputView;
import view.OutputView;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);

        LottoController controller = new LottoController(
                inputView,
                outputView,
                lottoFactory
        );
        controller.run();
    }
}
