package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusBall;
    private int count = 0;
    private boolean bonusFlag;

    public WinningLotto(String enteredWinningLotto, int bonusBall) {
        LottoParser lottoParser = new LottoParser();
        this.winningLotto = new Lotto(lottoParser.parseInput(enteredWinningLotto));
        validateBonusBall(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(int bonusBall) {
        if (bonusBall < 1 || bonusBall > 45) {
            throw new IllegalArgumentException("보너스 볼은 1부터 45 사이의 숫자여야 합니다.");
        }
        List<Integer> copiedWinningNumber = winningLotto.getLottoNumbers();
        Set<Integer> uniqueBonusball = new HashSet<>(copiedWinningNumber);
        if (!uniqueBonusball.add(bonusBall)) throw new IllegalArgumentException("중복된 로또 번호가 존재합니다.");
    }

    public int match(Lotto lotto) {
        count = 0;
        List<Integer> copiedLotto = lotto.getLottoNumbers();
        for (int i = 0; i < 6; i ++) {
            compareNumbers(copiedLotto, i);
        }
        if (count == 5) {
           bonusFlag = hasBonusNumber(copiedLotto);
        }
        return count;
    }
    public void compareNumbers(List<Integer> copiedLotto, int i) {
        if (copiedLotto.contains(winningLotto.get(i))) {
            count++;
        }
    }

    public boolean hasBonusNumber(List<Integer> copiedLotto) {
        return copiedLotto.contains(bonusBall);
    }

    public boolean getBonusFlag() {
        return bonusFlag;
    }
}
