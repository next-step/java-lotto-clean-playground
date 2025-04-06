package domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_NUMBER_COUNT = 6;

    Set<LottoNumber> lotto;

    private Lotto(Set<LottoNumber> lotto) {
        validateLotto(lotto);
        this.lotto = lotto;
    }

    public static Lotto from(List<Integer> numbers) {
        Set<LottoNumber> lotto = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toCollection(TreeSet::new));

        if (lotto.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        return new Lotto(lotto);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lotto);
    }

    private void validateLotto(Set<LottoNumber> lotto) {
        if (lotto.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다");
        }
    } 
}
