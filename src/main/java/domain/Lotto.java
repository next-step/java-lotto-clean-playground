package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record Lotto(List<Integer> lottoNumber) {

    private static final int LOTTO_NUMBER_SIZE = 6;

    public Lotto {
        validateSize(lottoNumber);
        validateDuplicate(lottoNumber);
    }

    private void validateSize(List<Integer> lottoNumber) {
        if (lottoNumber.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> lottoNumber) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(lottoNumber);
        if (nonDuplicateNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복되어선 안됩니다.");
        }
    }
}
