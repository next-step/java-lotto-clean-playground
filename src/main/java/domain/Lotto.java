package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Lotto {
    private static final List<Integer> NUMBERS = new ArrayList<>();
    public static final int MAX_NUMBER = 45;
    public static final int MIN_NUMBER = 1;
    public static final int LOTTO_SIZE = 6;

    static {
        for (int i = 1; i <= MAX_NUMBER; i++) {
            NUMBERS.add(i);
        }
    }

    private final List<Integer> numbers;

    public Lotto() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(numbers);
        this.numbers = new ArrayList<>(numbers.subList(0, 6));
        Collections.sort(this.numbers);
    }

    public Lotto(List<Integer> manualNumbers) {
        validateLottoNumber(manualNumbers);
        validateDuplication(manualNumbers);
        this.numbers = new ArrayList<>(manualNumbers);
        Collections.sort(this.numbers);
    }

    private void validateLottoNumber(List<Integer> manualNumbers) {
        for (int number : manualNumbers) {
            validateNumber(number);
        }
        if (manualNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지여야 합니다.");
        }
    }

    private void validateDuplication(List<Integer> manualNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(manualNumbers);
        if (uniqueNumbers.size() != manualNumbers.size()) {
            throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        return List.copyOf(numbers);
    }

    public int get(int index) {
        return numbers.get(index);
    }

}
