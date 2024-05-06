package domain;

import java.util.List;

public class WinningLotto {

	private final Lotto winningLotto;

	public WinningLotto(List<Integer> winningNumber) {
		validate(winningNumber);
		this.winningLotto = new Lotto(winningNumber);
	}

	private void validate(List<Integer> winningNumber) {
		if (winningNumber.size() != Lotto.LOTTO_SIZE) {
			throw new IllegalArgumentException("당첨 번호는 " + Lotto.LOTTO_SIZE + " 개의 숫자로 이루어져 있습니다.");
		}
	}

	public List<Integer> getWinningLotto() {
		return winningLotto.getLotto();
	}
}
