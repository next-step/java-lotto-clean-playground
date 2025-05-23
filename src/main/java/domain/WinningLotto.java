package domain;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusBall) {
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public Lotto getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    public boolean contains(LottoNumber number) {
        return winningNumbers.contains(number);
    }

    public int matchCount(Lotto other) {
        return (int) other.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean matchBonusBall(Lotto other) {
        return other.contains(bonusBall);
    }
}
