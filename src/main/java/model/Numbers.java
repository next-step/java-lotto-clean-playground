package model;

import java.util.ArrayList;
import java.util.List;

public class Numbers {

    private final List<Integer> numbers;

    public Numbers(final List<Integer> numbers) {
        this.numbers = numbers;
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
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
