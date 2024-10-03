package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        this.lotto = new ArrayList<>(lotto);

        checkSize();
        checkDuplicates();
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

    private void checkDuplicates() {
        Set<Integer> lottoSet = new HashSet<>(lotto);
        if(lottoSet.size() != lotto.size()){
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다.");
        }
    }

    private void checkRange(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 자연수여야 합니다.");
        }
    }

}
