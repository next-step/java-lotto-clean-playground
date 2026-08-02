package domain;

import java.util.*;

public class Lotto {
    private static final int NUMBER_COUNT = 6;
    private static final int RANDOM_NUMBER_BOUND = 45;

    private final Random random = new Random();
    private final List<LottoNumber> numbers = new ArrayList<>();

    public Lotto() {
        makeNumbers();
    }

    public Lotto(List<Integer> numberValues) {
        if (numberValues.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + NUMBER_COUNT + "개여야 합니다.");
        }

        if (new HashSet<>(numberValues).size() != numberValues.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }

        convertToLottoNumbers(numberValues);

        Collections.sort(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }

    private void makeNumbers() {
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(generateRandomNumber());
        }

        Collections.sort(numbers);
    }

    private LottoNumber generateRandomNumber() {
        LottoNumber lottoNumber;

        do {
            lottoNumber = new LottoNumber(random.nextInt(RANDOM_NUMBER_BOUND) + 1);
        } while (containsNumber(lottoNumber));

        return lottoNumber;
    }

    private boolean containsNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    private void convertToLottoNumbers(List<Integer> numberValues) {
        for (int number : numberValues) {
            LottoNumber lottoNumber = new LottoNumber(number);
            numbers.add(lottoNumber);
        }
    }
}
