package model;

import java.util.List;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> lottoNumbers) {
        long distinctCount = lottoNumbers.stream().distinct().count();
        if (distinctCount != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호에 중복이 있습니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }

    public String toStringLottoTickets() {
        List<Integer> sortedLottoNumbers = getSortedLottoNumbers();
        return sortedLottoNumbers.toString();
    }

    private List<Integer> getSortedLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .toList();
    }
}
