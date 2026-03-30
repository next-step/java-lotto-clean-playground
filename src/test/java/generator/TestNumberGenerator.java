package generator;

import domain.LottoNumber;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final List<LottoNumber> numbers;

    public TestNumberGenerator(List<LottoNumber> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    @Override
    public List<LottoNumber> generate() {
        return numbers;
    }
}
