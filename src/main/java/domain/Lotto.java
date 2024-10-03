package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        this.lotto = new ArrayList<>(lotto);

        checkSize();
        // LottoNumber 클래스를 만들다가 코드가 꼬여서 실패했습니다..ㅠㅠ
        for(Integer lottoNumber : lotto){
            checkRange(lottoNumber);
        }
    }

    public List<Integer> getLotto() {
        return lotto;
    }

    public int matchingNumbers(List<Integer> winningNumbers) {
        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void checkSize() {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void checkRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

}
