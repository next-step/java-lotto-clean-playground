package domain;

import java.util.Comparator;
import java.util.List;

public class LottoTicket {
    private static final int LOTTO_SIZE = 6;
    List<LottoNumber> lottoNumbers;

    public LottoTicket (List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public String toDisplayString() {
        return lottoNumbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::value))
                .toString();
    }

    public WinningRank getWinningRank(WinningNumbers winningNumbers) {
        int matchCount = countMatchNumbers(winningNumbers);
        boolean bonusMatched = lottoNumbers.contains(winningNumbers.getBonusNumber());
        return WinningRank.of(matchCount, bonusMatched);
    }

    private int countMatchNumbers(WinningNumbers winningNumbers) {
        int count = 0;
        for (LottoNumber winningNumber : winningNumbers.getLottoNumbers()) {
            if (lottoNumbers.contains(winningNumber)) {
                count++;
            }
        }
        return count;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers == null || numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
