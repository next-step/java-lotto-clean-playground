package domain.lotto;

import domain.common.ExceptionMessage;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int LOTTO_SIZE_LIMIT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        sortByAsc(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public int size() {
        return lottoNumbers.size();
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() == LOTTO_SIZE_LIMIT) {
            return;
        }

        throw new IllegalArgumentException(ExceptionMessage.LOTTO_NUMBERS_INVALID_SIZE);
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        HashSet<LottoNumber> hashSet = new HashSet<>(lottoNumbers);
        if (hashSet.size() < LOTTO_SIZE_LIMIT) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_VALUE);
        }
    }

    private void sortByAsc(List<LottoNumber> lottoNumbers) {
        lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    public boolean contains(LottoNumber winningNumber) {
        return lottoNumbers.contains(winningNumber);
    }

    public int getCorrectCount(LottoTicket winningTicket) {
        return lottoNumbers.stream()
            .filter(winningTicket::contains)
            .toList()
            .size();
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.stream()
            .anyMatch(bonusNumber::equals);
    }

    public static LottoTicket fromStringsInput(final String[] input) {
        final List<LottoNumber> numbers = Arrays.stream(input)
            .mapToInt(Integer::parseInt)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        return new LottoTicket(numbers);
    }
}
