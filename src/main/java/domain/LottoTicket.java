package domain;

import java.util.List;

public class LottoTicket {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public LottoTicket(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        if (numbers.stream().distinct().count() != SIZE) throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        if (numbers.stream().anyMatch(n -> n < MIN || n > MAX)) throw new IllegalArgumentException("로또 번호는 1~45 범위여야 합니다.");
    }

    public List<Integer> numbers() {
        return List.copyOf(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
