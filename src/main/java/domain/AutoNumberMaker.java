package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AutoNumberMaker {

    private static final int LOTTO_INIT_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoBalls = new ArrayList<>();

    public AutoNumberMaker() {
        initLottoBalls();
    }

    private void initLottoBalls() {
        for (int i = LOTTO_INIT_NUMBER; i <= LOTTO_END_NUMBER; i++) {
            lottoBalls.add(new LottoNumber(i));
        }
    }

    public List<LottoNumber> getLottoNumber() {
        List<LottoNumber> lottoNumber;

        shuffleLottoBalls();
        lottoNumber = getSelectedBalls();
        lottoNumber = sortLottoNumber(lottoNumber);

        return lottoNumber;
    }

    private void shuffleLottoBalls() {
        Collections.shuffle(lottoBalls);
    }

    private List<LottoNumber> getSelectedBalls() {
        List<LottoNumber> selectedBalls = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            selectedBalls.add(lottoBalls.get(i));
        }

        return selectedBalls;
    }

    private List<LottoNumber> sortLottoNumber(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::number))
                .collect(Collectors.toList());
    }
}
