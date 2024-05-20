package domain;

import java.util.List;

public class WinningLotto {

	private final Lotto winningLotto;
	private final LottoNumber bonusNumber;

	public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
		validate(winningLotto, bonusNumber);
		this.winningLotto = winningLotto;
		this.bonusNumber = bonusNumber;
	}

	public WinningLotto(Lotto winningLotto) {
		this.winningLotto = winningLotto;
		this.bonusNumber = null;
	}

	private void validate(Lotto winningLotto, LottoNumber bonusNumber) {
		if (winningLotto.getLotto().contains(bonusNumber.getLottoNumber())) {
			throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
		}
	}

	public List<Integer> getWinningLotto() {
		return winningLotto.getLotto();
	}

	public LottoNumber getBonusNumber() {
		return bonusNumber;
	}
}
