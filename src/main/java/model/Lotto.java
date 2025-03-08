package model;

import java.util.List;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicates(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumber::new)
                .toList();
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

    private void validateSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LottoConstants.LOTTO_NUMBERS_PER_TICKET.getValue()) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> lottoNumbers) {
        long distinctCount = lottoNumbers.stream().distinct().count();
        if (distinctCount != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호에 중복이 있습니다.");
        }
    }

    private List<Integer> getSortedLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .toList();
    }
}
