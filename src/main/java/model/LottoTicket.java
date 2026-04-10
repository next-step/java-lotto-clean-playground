package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    private final List<Integer> lottoNumbers;

    public LottoTicket(List<Integer> number) {
        this.lottoNumbers = new ArrayList<>(number);
        vaildateSize(number);
        validateDuplicate(number);
        validateRange(number);
        Collections.sort(this.lottoNumbers);
    }

    public int calculateMatch(List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean hasBonusBall(int BonusBall) {
        return lottoNumbers.contains(BonusBall);
    }

    public List<Integer> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }

    public void vaildateSize(List<Integer> number) {
        int LOTTO_VALID_COUNT=6;
        if (number.size() != LOTTO_VALID_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다");
        }
    }

    public void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호에 중복이 있습니다.");
        }
    }

    public void validateRange(List<Integer> numbers){
        for(Integer number:numbers){
            if(number<1||number>45){
                throw new IllegalArgumentException("로또 번호는 1~45 사이여야합니다.");
            }
        }
    }
}
