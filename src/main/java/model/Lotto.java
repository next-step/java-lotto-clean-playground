package model;

import java.util.*;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        validateLotto(lotto);
        this.lotto = getSortedLotto(lotto);
    }

    private void validateLotto(List<Integer> lotto) {
        if (lotto.size() != LottoConstants.NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException("로또 번호는 6개의 수로 이루어져야 합니다.");
        }

        lotto.forEach(lottoNumber -> {
            if (lottoNumber < LottoConstants.MIN_NUMBER.getValue() || lottoNumber > LottoConstants.MAX_NUMBER.getValue()) {
                throw new IllegalArgumentException("로또 번호는 " + LottoConstants.MIN_NUMBER.getValue() +
                        " 이상 " + LottoConstants.MAX_NUMBER.getValue() + " 이하여야 합니다.");
            }
        });

        Set<Integer> uniqueLottoNumbers = new HashSet<>(lotto);

        if (uniqueLottoNumbers.size() != LottoConstants.NUMBER_COUNT.getValue()) {
            throw new IllegalArgumentException("로또 번호는 중복되어서는 안 됩니다.");
        }
    }

    private List<Integer> getSortedLotto(List<Integer> lotto) {
        List<Integer> sortedLotto = new ArrayList<>(lotto);
        sortedLotto.sort(null);

        return sortedLotto;
    }

    public List<Integer> getValue() {
        return Collections.unmodifiableList(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
