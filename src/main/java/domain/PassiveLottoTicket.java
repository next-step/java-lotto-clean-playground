package domain;

import java.util.List;

public class PassiveLottoTicket {
    private final List<Integer> numbers;

    public PassiveLottoTicket(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
