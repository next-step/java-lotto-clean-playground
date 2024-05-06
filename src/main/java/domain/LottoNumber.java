package domain;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;

	private final int lottoNumber;

	public LottoNumber(int number) {
		validate(number);
		this.lottoNumber = number;
	}

	private void validate(int number) {
		if (number < MIN_NUMBER || number > MAX_NUMBER) {
			throw new IllegalArgumentException("로또 번호는 " + MIN_NUMBER + "과 " + MAX_NUMBER + " 사이여야 합니다.");
		}
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(lottoNumber, other.lottoNumber);
	}
}
