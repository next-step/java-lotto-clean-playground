package domain.lotto;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class LottoTicket {

    private static final int LOTTO_SIZE_LIMIT = 6;

    private final List<Integer> lottoNumbers;

    public LottoTicket(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        sortByAsc(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public int size() {
        return lottoNumbers.size();
    }

    private void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() == LOTTO_SIZE_LIMIT) {
            return;
        }

        throw new IllegalArgumentException("올바르지 않은 사이즈");
    }

    private void validateDuplicate(List<Integer> lottoNumbers) {
        HashSet<Integer> hashSet = new HashSet<>(lottoNumbers);
        if (hashSet.size() < LOTTO_SIZE_LIMIT) {
            throw new IllegalArgumentException("중복 값 포함");
        }
    }

    private void sortByAsc(List<Integer> lottoNumbers) {
        lottoNumbers.sort(Comparator.naturalOrder());
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public boolean contains(Integer winningNumber) {
        return lottoNumbers.contains(winningNumber);
    }
}
