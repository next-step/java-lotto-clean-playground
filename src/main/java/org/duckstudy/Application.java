package org.duckstudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.duckstudy.controller.LottoController;
import org.duckstudy.view.InputView;
import org.duckstudy.view.OutputView;

public class Application {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(bufferedReader);

        LottoController lottoController = new LottoController(outputView, inputView);
        lottoController.run();
    }
}
