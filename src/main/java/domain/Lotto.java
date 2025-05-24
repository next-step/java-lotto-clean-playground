package domain;

import java.util.*;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> list){
        validateSize(list);
        validateNoDuplicates(list);
        this.lotto = List.copyOf(list);
    }

    private void validateSize(List<LottoNumber> list) {
        if (list.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 반드시 6개여야 합니다.");
        }
    }

    private void validateNoDuplicates(List<LottoNumber> list) {
        Set<LottoNumber> unique = new HashSet<>(list);
        if (unique.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return lotto.toString();
    }

    public List<LottoNumber> getNumbers() {
        return lotto;
    }

}


