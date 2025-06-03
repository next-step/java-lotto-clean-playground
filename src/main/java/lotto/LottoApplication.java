package lotto;

import lotto.model.LottoGenerator;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoApplication {

    public static void main(String[] args) {
        LottoGame game = new LottoGame(
            new LottoGenerator()
            , new LottoInputView()
            , new LottoOutputView()
        );
        game.play();
    }
}
