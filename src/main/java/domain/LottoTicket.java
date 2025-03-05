package domain;

import java.util.Collections;
import java.util.List;

public class LottoTicket {
    private final List<LottoNumber> numbers;

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public LottoTicket(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 티켓은 6개의 번호를 가져야 합니다.");
        }
        if (!isUnique(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private boolean isUnique(List<LottoNumber> numbers) {
        return numbers.size() == numbers.stream().distinct().count();
    }

    public int countMatchingNumbers(List<LottoNumber> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean containsNumber(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
