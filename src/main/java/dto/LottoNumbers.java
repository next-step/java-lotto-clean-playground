package dto;

import java.util.List;

public record LottoNumbers(List<Integer> numbers) {

    @Override
    public String toString() {
        return numbers.toString();
    }
}
