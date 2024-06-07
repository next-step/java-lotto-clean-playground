package org.duckstudy.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
            return inputLottoNumber();
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }

    private List<Integer> inputLottoNumber() throws IOException {
        return Arrays.stream(bufferedReader.readLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }

    public int inputBonusNumber() {
        outputView.printInputBonusNumber();

        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }
}
