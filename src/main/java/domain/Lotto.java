package domain;

import static domain.LottoRule.LOTTO_NUMBERS_COUNT;
import static domain.LottoRule.MAX_NUMBER;
import static domain.LottoRule.MIN_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    private void validate(List<Integer> numbers){
        if(numbers.size() != new HashSet<>(numbers).size()){
            throw new IllegalArgumentException("중복된 로또 번호는 허용하지 않습니다.");
        }
        if(numbers.size() > LOTTO_NUMBERS_COUNT){
            throw  new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBERS_COUNT + "개를 넘을 수 없습니다.");
        }
        if(numbers.size() < LOTTO_NUMBERS_COUNT){
            throw new IllegalArgumentException("로또 번호는 반드시 " + LOTTO_NUMBERS_COUNT + "개를 입력하셔야 합니다.");
        }
        for(int i = 0; i < LOTTO_NUMBERS_COUNT; i++){
            validateRange(numbers.get(i));
        }
    }
    private void validateRange(int number){
        if(number > MAX_NUMBER || number < MIN_NUMBER){
            throw new IllegalArgumentException("로또 번호의 범위는 " + MIN_NUMBER + "보다 작거나, " + MAX_NUMBER + "보다 클 수 없습니다.");
        }
    }
    public List<Integer> getLottoNumbers(){
        return Collections.unmodifiableList(numbers);
    }

    public long countMatch(WinningNumbers winningNumbers) {
        return countMatch(winningNumbers.getLotto());
    }

    private long countMatch(Lotto other) {
        return other.numbers.stream().filter(this.numbers::contains).count();
    }
}
