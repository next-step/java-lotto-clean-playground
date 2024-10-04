package domain;

public class Lotto {
    private static final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    private LottoNumbers lottoNumbers;



    public Lotto(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto creatAutoLotto() {
        return new Lotto(lottoNumberGenerator.generateLottoNumbers());
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }
}
