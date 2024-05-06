package org.duckstudy.view;

import java.io.BufferedReader;
import java.io.IOException;

public class InputView {

    private final BufferedReader bufferedReader;
    private final OutputView outputView;

    public InputView(BufferedReader bufferedReader, OutputView outputView) {
        this.bufferedReader = bufferedReader;
        this.outputView = outputView;
    }

    public int inputPrice() throws IOException {
        outputView.printInputPrice();
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }
}
