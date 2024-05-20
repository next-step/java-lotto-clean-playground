package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		return numbers.stream()
			.anyMatch(number -> !set.add(number));
	}

	private boolean isNotAscending(List<Integer> numbers) {
		return IntStream.range(0, numbers.size() - 1)
			.anyMatch(i -> numbers.get(i) > numbers.get(i + 1));
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
		List<Integer> randomNumberList = new ArrayList<>();
		for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
			randomNumberList.add(i);
		}
		Collections.shuffle(randomNumberList);
		return randomNumberList;
	}

	public int getMatchedNumberCount(WinningLotto lottoWinningNumber) {
		return (int)lotto.stream()
			.filter(lottoNumber -> lottoWinningNumber.getWinningLotto().contains(lottoNumber.getLottoNumber()))
			.count();
	}

	public boolean isContainsBonusNumber(LottoNumber bonusNumber) {
		return lotto.contains(bonusNumber);
	}

	public List<Integer> getLotto() {
		return new LottoResponse(List.copyOf(lotto)).getLottoResponse();
	}

}
