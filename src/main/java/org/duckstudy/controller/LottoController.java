package org.duckstudy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.duckstudy.model.lotto.Lotto;
import org.duckstudy.model.lotto.Lottos;
import org.duckstudy.model.Price;
import org.duckstudy.view.InputView;
import org.duckstudy.view.OutputView;

public class LottoController {

    private final OutputView outputView;
    private final InputView inputView;

    public LottoController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() throws IOException {
        Price price = createPrice();
        Lottos lottos = createLottos(price.calculateLottoCount());
        outputView.printLottos(lottos.toList());
    }

    private Price createPrice() throws IOException {
        try {
            return new Price(inputView.inputPrice());
        } catch (NumberFormatException e) {
            outputView.printException(e);
            return createPrice();
        }
    }

    private Lottos createLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }
        return new Lottos(lottos);
    }
}
