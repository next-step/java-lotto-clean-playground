package domain;

import java.util.List;

public class WinningLotto {

    private List<Integer> winNumbers;

    public int count = 0;

    public WinningLotto(List<Integer> winNumbers) {
        this.winNumbers = winNumbers;
        checkSameLottoNumber();
    }

    private void checkSameLottoNumber() {
        for (Integer number : winNumbers) {
            if (winNumbers.contains(number)) {
                count++;
            }
        }
    }
}
