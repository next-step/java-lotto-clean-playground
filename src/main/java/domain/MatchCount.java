package domain;

import java.util.List;

public class MatchCount {
    int count = 0;
    public void comparingLotto (List<Integer> lottoNumbers, Lotto winningNumbers) {
        count = 0;
        for (int i = 0; i < 6; i ++) {
            compareNumbers(lottoNumbers, winningNumbers, i);
        }
    }

    public void compareNumbers(List<Integer> lottoNumbers, Lotto winningNumbers, int i) {
        if (lottoNumbers.contains(winningNumbers.getLottoNumbers().get(i))) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean hasBonusNumber(List<Integer> lottoNumber, int bonusNumber) {
        return lottoNumber.contains(bonusNumber);
    }
}
