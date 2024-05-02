import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        long duplicatedSize = numbers.stream().distinct().count();
        if (numbers.size() != duplicatedSize) {
            throw new IllegalArgumentException();
        }
    }

    public List<LottoNumber> numbers() {
        return List.copyOf(numbers);
    }
}
