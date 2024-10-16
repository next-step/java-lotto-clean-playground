package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static utils.LottoNumberGenerator.LOTTO_SIZE;
import static utils.LottoNumberGenerator.MAX_NUMBER;
import static utils.LottoNumberGenerator.MIN_NUMBER;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        validate(lotto);
        this.lotto = new ArrayList<>(lotto);
    }

    private void validate(List<Integer> checkLotto){
        checkSize(checkLotto);
        checkDuplicates(checkLotto);
        for(Integer lottoNumber :checkLotto){
            checkRange(lottoNumber);
        }
    }

    private void checkSize(List<Integer> checkLotto) {
        if (checkLotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void checkDuplicates(List<Integer> checkLotto) {
        Set<Integer> lottoSet = new HashSet<>(checkLotto);
        if(lottoSet.size() != checkLotto.size()){
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다.");
        }
    }

    private void checkRange(int lottoNumber) {
        if (lottoNumber < MIN_NUMBER || lottoNumber > MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 자연수여야 합니다.");
        }
    }

    public int matchingNumbers(List<Integer> winningNumbers) {
        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }
}
