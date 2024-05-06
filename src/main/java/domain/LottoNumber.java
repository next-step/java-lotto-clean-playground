package domain;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int MIN_NUMBER = 1;
	public static final int MAX_NUMBER = 45;

	private final int lottoNumber;

	public LottoNumber(int number) {
		this.lottoNumber = number;
	}

	public int getLottoNumber() {
		return lottoNumber;
	}

	@Override
	public int compareTo(LottoNumber other) {
		return Integer.compare(lottoNumber, other.lottoNumber);
	}
}
