package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Numbers {

    private final static int ELEMENT_COUNT = 6;

    private final List<Integer> numbers;

    public Numbers(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(final List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateCommonElements(numbers);
    }

    private void validateNumbersSize(final List<Integer> numbers) {
        final int count = numbers.size();
        if (count < ELEMENT_COUNT) {
            throw new IllegalArgumentException(
                    "로또 숫자 개수가 " + (ELEMENT_COUNT - count) + "개 부족합니다.");
        }
        if (count > ELEMENT_COUNT) {
            throw new IllegalArgumentException(
                    "로또 숫자 개수가 " + (count - ELEMENT_COUNT) + "개 초과합니다.");
        }
    }

    private void validateCommonElements(final List<Integer> numbers) {
        Set<Integer> numbersNotCommon = new HashSet<>(numbers);
        if (numbersNotCommon.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 숫자는 서로 중복되어선 안됩니다.");
        }
    }

    public int getCommonCount(final Numbers otherNumbers) {
        List<Integer> copyNumbers = new ArrayList<>(this.numbers);
        copyNumbers.retainAll(otherNumbers.getNumbers());
        return copyNumbers.size();
    }

    public boolean containBonusNumber(final int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
