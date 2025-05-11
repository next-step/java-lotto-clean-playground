package dto;

import java.util.List;

public record LottoNumbersDto(List<Integer> numbers) {

    @Override
    public String toString() {
        return numbers.toString();
    }
}
