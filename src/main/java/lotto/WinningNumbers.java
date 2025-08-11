package lotto;

public class WinningNumbers {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    private WinningNumbers(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers of(LottoTicket winningTicket, LottoNumber bonusNumber) {
        return new WinningNumbers(winningTicket, bonusNumber);
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}


