package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberMaker {

    List<Integer> lottoBalls = new ArrayList<>();

    public LottoNumberMaker() {
        initLottoBalls();
    }

    private void initLottoBalls() {
        for (int i = 1; i <= 45; i++) {
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
        for (int i = 0; i < 6; i++) {
            selectedBalls.add(lottoBalls.get(i));
        }

        return selectedBalls;
    }

    private void sortLottoNumber(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }
}
