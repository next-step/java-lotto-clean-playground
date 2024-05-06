package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private List<LottoNumber> numbers = new ArrayList<>();

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != Constant.SIX_COUNT) {
            throw new IllegalArgumentException(Constant.SIZE_EXCEPTION);
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<Integer> numberSet = new HashSet<>();
        for (LottoNumber number : numbers) {
            numberSet.add(number.getNumber());
        }
        if (numberSet.size() != Constant.SIX_COUNT) {
            throw new IllegalArgumentException(Constant.DUPLICATE_EXCEPTION);
        }
    }
}
