package model;

import java.util.*;

public class LottoNumbers {
    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validateSize(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호가 6개가 아닙니다");
        }
    }

    public LottoNumbers sortNumbers() {
        List<LottoNumber> sortNumber = new ArrayList<>(numbers);
        Collections.sort(sortNumber, Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoNumbers(sortNumber);
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumbers)) return false;
        LottoNumbers lottoNumbers = (LottoNumbers) o;
        return Objects.equals(numbers, lottoNumbers.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
