package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private List<LottoNumber> lotto = new ArrayList<>();

    public static final int LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> lotto) {
        validate(lotto);
        this.lotto = lotto;
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        long duplicatedSize = checkDuplicatedSize(numbers);
        if (numbers.size() != duplicatedSize) {
            throw new IllegalArgumentException("로또는 중복되지 않는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    private int checkDuplicatedSize(List<LottoNumber> numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (LottoNumber lottoNumber : numbers) {
            numbersList.add(lottoNumber.getLottoNumber());
        }
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbersList);
        return nonDuplicateNumbers.size();
    }

    public boolean iscontained(int number) {
        for (LottoNumber lottoNumber : lotto) {
            if (lottoNumber.getLottoNumber() == number) return true;
        }
        return false;
    }
}
