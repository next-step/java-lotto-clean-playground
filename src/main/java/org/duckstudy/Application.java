package org.duckstudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.duckstudy.game.LottoGame;
import org.duckstudy.io.InputView;
import org.duckstudy.io.OutputView;

public class Application {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(bufferedReader);

        LottoGame lottoGame = new LottoGame(outputView, inputView);
        lottoGame.run();
    }
}
