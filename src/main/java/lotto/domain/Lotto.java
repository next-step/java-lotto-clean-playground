package lotto.domain;

import static lotto.domain.WinningNumbers.WINNING_NUMBER_COUNT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;


    public Lotto(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateSize(numbers);
        this.lottoNumbers = toLottoNumbers(numbers);
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }
    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public int countMatch(WinningNumbers winningNumbers) {
        int matchCount = 0;
        for (LottoNumber lottoNumber : lottoNumbers) {
            matchCount += matchScore(lottoNumber, winningNumbers);
        }
        return matchCount;
    }

    private int matchScore(LottoNumber number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            return 1;
        }
        return 0;
    }


}
