package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_NUMBER_BOUNDARY = 6;
    private static final List<Integer> lottoNumber = new ArrayList<>();
    private final List<Integer> getLottoNumber;

    public Lotto(List<Integer> getLottoNumber) {
        this.getLottoNumber = getLottoNumber;
    }

    private static void makeLottoNumber() {
        for (int i = FIRST_LOTTO_NUMBER; i < LAST_LOTTO_NUMBER + FIRST_LOTTO_NUMBER; i++) {
            lottoNumber.add(i);
        }
    }

    private static void shuffleLottoNumber() {
        Collections.shuffle(lottoNumber);
    }

    private static void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }

    public static Lotto getLotto() {
        makeLottoNumber();
        shuffleLottoNumber();
        List<Integer> buyLotto = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < LOTTO_NUMBER_BOUNDARY; i++) {
            buyLotto.add(lottoNumber.get(i));
        }
        sortLottoNumber(buyLotto);
        return new Lotto(buyLotto);
    }

    public List<Integer> getLottoNumber() {
        return getLottoNumber;
    }
}
