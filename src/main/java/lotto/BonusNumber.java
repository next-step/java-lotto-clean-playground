package lotto;

public class BonusNumber {
    private final LottoNumber bonus;

    public BonusNumber(int number, InputLottoNumber winningNumbers) {
        LottoNumber lottoNumber = new LottoNumber(number);
        if (winningNumbers.contains(lottoNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        this.bonus = lottoNumber;
    }

    public boolean isMatch(InputLottoNumber ticket) {
        return ticket.contains(bonus);
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
