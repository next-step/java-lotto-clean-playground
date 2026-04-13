package domain;

public class WinningLotto {
    private final Lotto winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winnerNumbers, LottoNumber bonusNumber) {
        validate(winnerNumbers, bonusNumber);
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank judge(Lotto userLotto) {
        int matchCount = userLotto.getMatchCount(winnerNumbers);
        boolean matchBonus = userLotto.contains(bonusNumber);
        return Rank.find(matchCount, matchBonus);
    }
}
