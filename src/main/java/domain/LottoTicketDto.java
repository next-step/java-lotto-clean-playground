
package domain;

import java.util.List;

public class LottoTicketDto {

    private final List<Integer> numbers;

    public LottoTicketDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
