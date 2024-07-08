package lotto;

import lotto.controller.LottoController;
import lotto.utils.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        final LottoController controller = new LottoController(
            new InputView(),
            new OutputView(),
            new NumberGenerator()
        );

        controller.run();
    }
}
