package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        validateLottoNumbers();
    }

    private void validateLottoNumbers() {
        validateSize();
        validateRange();
        validateDuplicate();
    }

    private void validateSize() {
            if (lottoNumbers.size() != LOTTO_SIZE) {
                throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
            }
    }

    private void validateRange() {
        for(int number : lottoNumbers) {
            validateNumberRange(number);
        }
    }

    private void validateNumberRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1~45 사이여야 합니다.");
        }
    }

    private void validateDuplicate() {
        List<Integer> temp = new ArrayList<>();

        for(int number : lottoNumbers) {
            validateNumberDuplicate(number, temp);
            temp.add(number);
        }
    }

    private void validateNumberDuplicate(int number, List<Integer> temp) {
        if (temp.contains(number)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

}
