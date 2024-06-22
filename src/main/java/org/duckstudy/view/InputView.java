package org.duckstudy.view;

import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class InputView {

    private final BufferedReader bufferedReader;

    public InputView(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public int inputPrice() {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }

    public Set<Integer> inputWinningLotto() {
        try {
            return inputLottoNumber();
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }

    private Set<Integer> inputLottoNumber() throws IOException {
        return Arrays.stream(bufferedReader.readLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toSet());
    }

    public int inputBonusNumber() {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    public int inputManualLottoCount() {
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.");
        }
    }

    public Set<Integer> inputManualLotto() {
        try {
            return inputLottoNumber();
        } catch (NumberFormatException | IOException e) {
            throw new NumberFormatException("숫자만 입력 가능합니다.\n");
        }
    }
}
