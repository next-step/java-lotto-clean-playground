package domain;

import java.util.List;

public class MatchCount {
    int count = 0;
    public void comparingLotto (List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        count = 0;
        for (int i = 0; i < 6; i ++) {
            compareNumbers(lottoNumbers, winningNumbers, i);
        }
    }

    public void compareNumbers(List<Integer> lottoNumbers, List<Integer> winningNumbers, int i) {
        if (lottoNumbers.contains(winningNumbers.get(i))) {
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}
