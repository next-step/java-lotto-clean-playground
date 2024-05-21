package org.duckstudy.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class InputView {

    private final BufferedReader bufferedReader;
    private final OutputView outputView;

    public InputView(BufferedReader bufferedReader, OutputView outputView) {
        this.bufferedReader = bufferedReader;
        this.outputView = outputView;
    }

    public int inputPrice() {
        outputView.printInputPrice();
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }

    public List<Integer> inputWinningLotto() {
        outputView.printInputWinningLotto();

        try {
            String line = bufferedReader.readLine();

            return Stream.of(line.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }
}
