package domain;

public class Lotto {
    public static final int PRICE = 1000;
    private static final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    private LottoNumbers lottoNumbers;



    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto creatAutoLotto() {
        return new Lotto(lottoNumberGenerator.generateLottoNumbers());
    }
}
