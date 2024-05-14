package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_NUMBER_BOUNDARY = 6;
    private static final int DIVIDE_INPUT_MONEY = 1000;
    private final int lottoMoney;
    private final List<Integer> lottoNumber = new ArrayList<>();

    public Lotto(int lottoMoney) {
        this.lottoMoney = lottoMoney;
    }

    public void makeLottoNumber() {
        for (int i = FIRST_LOTTO_NUMBER; i < LAST_LOTTO_NUMBER + FIRST_LOTTO_NUMBER; i++) {
            lottoNumber.add(i);
        }
    }

    public int getLottoCount() {
        return lottoMoney / DIVIDE_INPUT_MONEY;
    }

    private void shuffleLottoNumber() {
        Collections.shuffle(lottoNumber);
    }

    public List<Integer> getLotto() {
        shuffleLottoNumber();
        List<Integer> getLotto = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < LOTTO_NUMBER_BOUNDARY; i++) {
            getLotto.add(lottoNumber.get(i));
        }
        sortLottoNumber(getLotto);
        return getLotto;
    }

    private void sortLottoNumber(List<Integer> lottoNumber){
       Collections.sort(lottoNumber);
    }

    public List<List<Integer>> sumOfLotto(){
        List<List<Integer>> sumOfLotto = new ArrayList<>();
        for(int i=INITIAL_NUMBER;i<getLottoCount();i++){
            List<Integer> getLotto = getLotto();
            sumOfLotto.add(getLotto);
        }
        return sumOfLotto;
    }
}
