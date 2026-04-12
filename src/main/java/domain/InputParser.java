package domain;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static Lotto parseToLotto(String input) {
        String[] tokens = input.split(", ");
        List<LottoNumber> winningNumbers = new ArrayList<>();
        for (String token : tokens) {
            int number = Integer.parseInt(token.trim());
            winningNumbers.add(new LottoNumber(number));
        }
        return new Lotto(winningNumbers);
    }

    public static LottoNumber parseToLottoNumber(String input) {
        return new LottoNumber(Integer.parseInt(input));
    }
}
