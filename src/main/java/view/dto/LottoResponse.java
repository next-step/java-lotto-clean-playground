package view.dto;

import java.util.List;
import model.Lotto;
import model.LottoNumber;

public record LottoResponse(
        List<Integer> numbers
) {

    public static LottoResponse from(Lotto lottos) {
        List<Integer> numbers = lottos.numbers().stream()
                .map(LottoNumber::number)
                .toList();
        return new LottoResponse(numbers);
    }
}
