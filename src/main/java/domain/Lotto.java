package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lottoNumber;

    public Lotto(List<Integer> lottoNumber) {
        validate(lottoNumber);
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validate(List<Integer> lottoNumber) {
        if (lottoNumber.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(lottoNumber).size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }


    private void validateRange(List<Integer> lottoNumber) {
        for (Integer number : lottoNumber) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(lottoNumber);
    }
}
