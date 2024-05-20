package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoNumberGenerator implements CreateLottoNumber {

    private static final Random radom = new Random();
    private static final int INITIAL_NUMBER = 0;
    private static final int FIRST_LOTTO_NUMBER = 1;
    private static final int LAST_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_LENGTH_BOUNDARY = 6;

    @Override
    public List<Integer> getRandomLottoNumber() {
        List<Integer> buyLotto = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < LOTTO_NUMBER_LENGTH_BOUNDARY; i++) {
            int randomLottoNumber = checkDuplicateNumber(buyLotto, radom.nextInt(FIRST_LOTTO_NUMBER,LAST_LOTTO_NUMBER));
            buyLotto.add(randomLottoNumber);
        }
        return buyLotto;
    }

    private int checkDuplicateNumber(List<Integer> buyLotto, int randomNumber) {
        if (buyLotto.contains(randomNumber)) {
            return checkDuplicateNumber(buyLotto, radom.nextInt(FIRST_LOTTO_NUMBER,LAST_LOTTO_NUMBER));
        }
        return randomNumber;
    }
}
