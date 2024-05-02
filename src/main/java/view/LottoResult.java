package view;

import java.util.List;
import model.Lotto;
import model.LottoNumber;

public record LottoResult(
        List<Integer> numbers
) {

    public static LottoResult from(Lotto lottos) {
        List<Integer> numbers = lottos.numbers().stream()
                .map(LottoNumber::number)
                .toList();
        return new LottoResult(numbers);
    }
}