package lotto.model;

import static lotto.global.Constants.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.global.Rank;
import lotto.utils.generator.Generator;

public class Lotto {
    private final Set<LottoNumber> numbers;

    public Lotto(final Set<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(final Generator generator) {
        Set<Integer> numbers = generator.generate(MIN_NUMBER, MAX_NUMBER, NUMBERS_SIZE);
        Set<LottoNumber> lottoNumbers = numbers.stream()
            .map(LottoNumber::from)
            .collect(Collectors.toSet());

        return new Lotto(lottoNumbers);
    }

    public static Lotto from(String input) {
        Set<LottoNumber> lotto = Arrays.stream(input.split(", "))
            .map(Integer::parseInt)
            .map(LottoNumber::from)
            .collect(Collectors.toSet());

        return new Lotto(lotto);
    }

    public Rank getRank(WinNumbers winNumbers) {
        Set<LottoNumber> retain = new HashSet<>(numbers);
        retain.retainAll(winNumbers.getWinNumbers());

        return Rank.getRank(retain.size(), numbers.contains(winNumbers.getBonusNumber()));
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers.toArray());
    }

}
