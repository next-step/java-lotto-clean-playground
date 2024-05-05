package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
	private final List<Integer> lotto = new ArrayList<>();

	public Lotto() {
		generateLotto();
	}

	private void generateLotto() {
		List<Integer> randomNumberList = generateRandomNumberList();
		for (int i = 0; i < LottoGame.LOTTO_LENGTH; i++) {
			lotto.add(randomNumberList.get(i));
		}
		Collections.sort(lotto);
	}

	private List<Integer> generateRandomNumberList() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= LottoGame.LOTTO_MAX_NUMBER; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		return numbers;
	}

	@Override
	public String toString() {
		return lotto.toString();
	}
}
