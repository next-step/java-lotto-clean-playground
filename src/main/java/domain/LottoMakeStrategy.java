package domain;

public interface LottoMakeStrategy {
    public static final int START_EXTRACT_INDEX = 0;
    public static final int LOTTO_NUMBER_COUNT = 6;

    public Lottos makeLottos();

}
