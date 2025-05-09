package domain;

public class LottoCount {

    private final int lottoCount;

    private LottoCount(int lottoCount) {
        if (lottoCount < 0) {
            throw new IllegalArgumentException("로또 개수는 0 이상이어야 합니다");
        }
        this.lottoCount = lottoCount;
    }

    public static LottoCount from(int lottoCount) {
        return new LottoCount(lottoCount);
    }

    public int getLottoCount() {
        return lottoCount;
    }
}
