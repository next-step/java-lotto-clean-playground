package util;

import domain.Lotto;

import java.util.Arrays;

public class Parser {

    private Parser() {
    }

    public static Lotto parseLotto(String lottoString) {
        return new Lotto(Arrays.stream(lottoString.split("\\s*,\\s*"))
                .map(Integer::parseInt)
                .toList());
    }
}
