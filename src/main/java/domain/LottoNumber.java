package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {
    private static final List<Integer> NUMBERS = new ArrayList<>();
    static {
        for (int i = 1; i <= 45; i++) {
            NUMBERS.add(i);
        }
    }

    private final List<Integer> lottoNumbers;

    public LottoNumber() {
        List<Integer> numbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(numbers);
        this.lottoNumbers = new ArrayList<>(numbers.subList(0, 6));
        Collections.sort(this.lottoNumbers);
    }

    public LottoNumber(List<Integer> manualNumbers) {
        validateLottoNumber(manualNumbers);
        this.lottoNumbers = new ArrayList<>(manualNumbers);
        Collections.sort(this.lottoNumbers);
    }

    private void validateLottoNumber(List<Integer> manualNumbers) {
        for (int number : manualNumbers) {
            validateNumber(number);
        }
        if (manualNumbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1부터 45까지여야 합니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
