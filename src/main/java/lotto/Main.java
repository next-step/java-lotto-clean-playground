package lotto;

import lotto.controller.LottoController;
import lotto.model.LottoMarket;
import lotto.utils.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        final LottoController controller = new LottoController(
            new InputView(),
            new OutputView(),
            new LottoMarket(new NumberGenerator())
        );

        controller.run();
    }
}
