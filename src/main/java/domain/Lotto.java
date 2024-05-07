package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import response.LottoResponse;

public class Lotto {

	public static final int LOTTO_SIZE = 6;

	private final List<LottoNumber> lotto;

	public Lotto() {
		this.lotto = generateLotto();
	}

	public Lotto(List<Integer> numbers) {
		validate(numbers);
		this.lotto = numbers.stream()
			.map(number -> new LottoNumber(number))
			.collect(Collectors.toList());
	}

	private void validate(List<Integer> numbers) {
		if (numbers.size() != LOTTO_SIZE) {
			throw new IllegalArgumentException("로또는 숫자 " + LOTTO_SIZE + "개로 이루어져야 합니다.");
		}
		if (isDuplicate(numbers)) {
			throw new IllegalArgumentException("로또는 중복 없는 숫자 " + LOTTO_SIZE + "개로 이루어져야 합니다.");
		}
		if (isNotAscending(numbers)) {
			throw new IllegalArgumentException("로또는 오름차순으로 정렬되어야 합니다.");
		}
	}

	private boolean isDuplicate(List<Integer> numbers) {
		Set<Integer> set = new HashSet<>();
		for (Integer number : numbers) {
			if (set.contains(number)) {
				return true;
			}
			set.add(number);
		}
		return false;
	}

	private boolean isNotAscending(List<Integer> numbers) {
		for (int i = 0; i < numbers.size() - 1; i++) {
			if (numbers.get(i) > numbers.get(i + 1)) {
				return true;
			}
		}
		return false;
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

	public int getMatchedNumberCount(WinningLotto lottoWinningNumber) {
		int matchedNumberCount = 0;
		for (LottoNumber lottoNumber : lotto) {
			if (lottoWinningNumber.getWinningLotto().contains(lottoNumber.getLottoNumber())) {
				matchedNumberCount += 1;
			}
		}
		return matchedNumberCount;
	}

	public boolean isContainsBonusNumber(LottoNumber bonusNumber) {
		return lotto.contains(bonusNumber);
	}

	public List<Integer> getLotto() {
		return new LottoResponse(List.copyOf(lotto)).getLottoResponse();
	}

}
