package config;

import controller.LottoController;
import util.InputConverter;
import view.LottoInputView;
import view.LottoOutputView;

public class LottoConfiguration {

    public LottoController lottoController() {
        return new LottoController(
                lottoInputView(),
                lottoOutputView(),
                inputConverter()
        );
    }

    public LottoInputView lottoInputView() {
        return new LottoInputView();
    }

    public LottoOutputView lottoOutputView() {
        return new LottoOutputView();
    }

    public InputConverter inputConverter() {
        return new InputConverter();
    }

}
