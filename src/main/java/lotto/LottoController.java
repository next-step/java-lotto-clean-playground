package lotto;

import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class LottoController {

    public static void main(String[] args) {
        LottoService service = new LottoService(new LottoInputView(), new LottoOutputView());
        service.start();
    }
}
