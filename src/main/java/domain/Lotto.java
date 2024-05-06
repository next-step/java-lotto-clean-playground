package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import response.LottoResponse;

public class Lotto {

	public static final int LOTTO_SIZE = 6;

	private final List<LottoNumber> lotto;

	public Lotto() {
		this.lotto = generateLotto();
	}

	public Lotto(List<Integer> numbers) {
		this.lotto = numbers.stream()
			.map(number -> new LottoNumber(number))
			.collect(Collectors.toList());
	}

	private List<LottoNumber> generateLotto() {
		List<Integer> randomNumberList = generateRandomNumberList();
		List<LottoNumber> generatedLotto = new ArrayList<>();
		for (int i = 0; i < LOTTO_SIZE; i++) {
			generatedLotto.add(new LottoNumber(randomNumberList.get(i)));
		}
		Collections.sort(generatedLotto);
		return generatedLotto;
	}

	private List<Integer> generateRandomNumberList() {
		List<Integer> numbers = new ArrayList<>();
		for (int i = 1; i <= LottoNumber.MAX_NUMBER; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		return numbers;
	}

	public int getMatchedNumberCount(List<Integer> lottoWinningNumber) {
		int matchedNumberCount = 0;
		for (int i = 0; i < LOTTO_SIZE; i++) {
			if (lotto.get(i).getLottoNumber() == lottoWinningNumber.get(i)) {
				matchedNumberCount += 1;
			}
		}
		return matchedNumberCount;
	}

	public List<Integer> getLotto() {
		return new LottoResponse(List.copyOf(lotto)).getLottoResponse();
	}
}
