package domain.lotto;

public final class Lotto {

    public static final int PRICE = 1000;

    private final LottoNumbers lottoNumbers;

    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public int countMatchingNumbers(Lotto other) {
        return lottoNumbers.countMatching(other.lottoNumbers);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
