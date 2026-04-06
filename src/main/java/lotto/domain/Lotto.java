package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private static final List<Integer> ALL_NUMBERS = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
    private final List<LottoNumber> numbers;

    private Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(numbers.stream().map(LottoNumber::new).collect(Collectors.toList()));
    }

    public static Lotto generateRandom() {
        List<Integer> shuffleNumbers = new ArrayList<>(ALL_NUMBERS);
        Collections.shuffle(shuffleNumbers);
        return from(shuffleNumbers.subList(0, LOTTO_SIZE));
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int countMatch(Lotto other) {
        return (int) numbers.stream().filter(other.numbers::contains).count();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    //toString을 또 써주는 이유 : 각 클래스는 자기 역할에 맞게 따로 toString을 가져야 함
    //LottoNumber에서는 하나의 숫자를 string으로 가지며, 여기서는 6개 묶음을.
}
