import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;


    public Lotto(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static void validate(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new RuntimeException("로또 번호는 6개여야 합니다.");
        }
        if (lottoNumbers.stream().anyMatch(n -> n < MIN_LOTTO_NUMBER || n > MAX_LOTTO_NUMBER)) {
            throw new RuntimeException("로또 번호는 1이상 45이하여야 합니다.");
        }
    }

    @Override
    public String toString(){
        return numbers.toString();
    }

}
