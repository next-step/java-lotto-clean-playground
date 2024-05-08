package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoNumberMaker {

    private static final int LOTTO_INIT_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> lottoBalls = new ArrayList<>();

    public AutoNumberMaker() {
        initLottoBalls();
    }

    private void initLottoBalls() {
        for (int i = LOTTO_INIT_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            lottoBalls.add(i);
        }
    }

    public List<Integer> getLottoNumber() {
        List<Integer> lottoNumber;

        shuffleLottoBalls();
        lottoNumber = getSelectedBalls();
        sortLottoNumber(lottoNumber);

        return lottoNumber;
    }

    private void shuffleLottoBalls() {
        Collections.shuffle(lottoBalls);
    }

    private List<Integer> getSelectedBalls() {
        List<Integer> selectedBalls = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            selectedBalls.add(lottoBalls.get(i));
        }

        return selectedBalls;
    }

    private void sortLottoNumber(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }
}
