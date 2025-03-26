package model;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_PICK_NUMBER = 6;
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto create(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        return new Lotto(numbers);
    }

    public Ranking calculateRanking(Lotto winningNumbers) {
        int matchingCount = (int) numbers.stream()
                .filter(number -> winningNumbers.getNumbers().contains(number))
                .count();
        return Ranking.getRanking(matchingCount);
    }


    private static void validateLottoNumbers(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicatedLotto(numbers);
        validateLottoBound(numbers);
    }

    private static void validateLottoBound(List<Integer> numbers) {
        boolean result = numbers.stream().anyMatch(number -> number > LOTTO_MAX_NUMBER || number < LOTTO_MIN_NUMBER);
        if (result) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하의 정수입니다!");
        }
    }

    private static void validateDuplicatedLotto(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_PICK_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다!");
        }
    }

    private static void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_PICK_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 6자리 입니다!");
        }
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
