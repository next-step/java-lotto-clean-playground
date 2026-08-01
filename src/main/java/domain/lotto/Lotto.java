package domain.lotto;

import domain.lotto.wrap.LottoNumber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<LottoNumber> getNumbers() {
        List<LottoNumber> copied = new ArrayList<>();
        for (LottoNumber number :  numbers) {
            copied.add(new LottoNumber(number.getValue()));
        }
        return copied;
    }

    public int countMatch(Lotto other) {
        return (int) numbers.stream().filter(other::contains).count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("추첨된 로또 숫자는 " + SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (new HashSet(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("추첨된 로또 번호는 서로 중복될 수 없습니다.");
        }
    }
}
