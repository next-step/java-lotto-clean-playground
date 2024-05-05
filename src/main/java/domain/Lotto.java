package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {
	private final List<Integer> lotto = new ArrayList<>();

	public Lotto() {
		generateLotto();
	}

	private void generateLotto() {
		for (int i = 0; i < LottoGame.LOTTO_LENGTH; i++) {
			lotto.add(generateRandomNumber());
		}
	}

	private int generateRandomNumber() {
		return new Random().nextInt(LottoGame.LOTTO_MAX_NUMBER);
	}

	@Override
	public String toString() {
		return lotto.toString();
	}
}
