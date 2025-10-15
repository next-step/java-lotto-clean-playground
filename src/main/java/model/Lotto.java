package model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_TICKET_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> initialNumbers) {
        Set<LottoNumber> lottoNumbers = convertToLottoNumberSet(initialNumbers);
        validateSize(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private void validateSize(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복 없이" + Lotto.LOTTO_TICKET_SIZE + "개여야 합니다.");
        }
    }

    private Set<LottoNumber> convertToLottoNumberSet(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public int countMatches(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean IsBonusBallMatch(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    public String toString() {
        return numbers.toString();
    }
}
