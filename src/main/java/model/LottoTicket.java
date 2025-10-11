package model;

import java.util.*;

public class LottoTicket {
    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateSize(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호가 6개가 아닙니다");
        }
    }

    public LottoTicket sortNumbers() {
        List<LottoNumber> sortNumber = new ArrayList<>(numbers);
        Collections.sort(sortNumber, Comparator.comparingInt(LottoNumber::getNumber));
        return new LottoTicket(sortNumber);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

}
