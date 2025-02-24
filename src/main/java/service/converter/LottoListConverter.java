package service.converter;

import domain.model.Lotto;

import java.util.List;

public class LottoListConverter {
    public static Lotto listToLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static List<Integer> lottoToList(Lotto lotto) {
        return lotto.numbers();
    }
}
