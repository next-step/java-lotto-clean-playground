package domain;

public class Lotto {
    public static final int PRICE = 1000;
    private LottoNumbers lottoNumbers;
    private final RandomStrategy lottoRandomStrategy = new LottoRandomStrategy();



    public Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

//    public Lotto createLotto() {
//
//        return new Lotto(LottoNumbers.createDifferentLottoNumbers());
//    }

}
