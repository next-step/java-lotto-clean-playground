package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto from(List<LottoNumber> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
        return new Lotto(numbers);
    }

    private static void validateCount(List<LottoNumber> numbers) {
        if(numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }




}
