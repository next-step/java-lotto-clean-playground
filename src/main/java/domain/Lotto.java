package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
	private final List<Integer> lotto;

	public Lotto() {
		this.lotto = generateLotto();
	}

	private List<Integer> generateLotto() {
		List<Integer> randomNumberList = generateRandomNumberList();
		List<Integer> generatedLotto = new ArrayList<>();
		for (int i = 0; i < LottoGame.LOTTO_LENGTH; i++) {
			generatedLotto.add(randomNumberList.get(i));
		}
		Collections.sort(generatedLotto);
		return generatedLotto;
	}

	private List<Integer> generateRandomNumberList() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= LottoGame.LOTTO_MAX_NUMBER; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		return numbers;
	}

	public int getMatchedNumberCount(List<Integer> lottoWinningNumber) {
		int matchedNumberCount = 0;
		for (int i = 0; i < LottoGame.LOTTO_LENGTH; i++) {
			if (lotto.contains(lottoWinningNumber.get(i))) {
				matchedNumberCount += 1;
			}
		}
		return matchedNumberCount;
	}

	@Override
	public String toString() {
		return lotto.toString();
	}
}
