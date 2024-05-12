package org.duckstudy.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.duckstudy.model.Price;
import org.duckstudy.model.lotto.Lotto;
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
        List<Lotto> lottos = createLottos(price.calculateLottoCount());
        outputView.printLottos(lottos);
    }

    private Price createPrice() throws IOException {
        try {
            return new Price(inputView.inputPrice());
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return createPrice();
        }
    }

    private List<Lotto> createLottos(int lottoCount) {
        return Stream.generate(Lotto::createRandomLotto)
                .limit(lottoCount)
                .toList();
    }
}
