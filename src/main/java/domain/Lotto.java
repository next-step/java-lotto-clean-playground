package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Lotto를 관리하는 일급 컬렉션
public class Lotto {
    private final List<LottoNumber> numbers;

    //automated constructor
    public Lotto() {
        List<LottoNumber> cardinateNumbers = new ArrayList<>();
        for(int i=1; i<=45; i++){
            cardinateNumbers.add(new LottoNumber(i));
        }
        Collections.shuffle(cardinateNumbers);
        this.numbers = cardinateNumbers.subList(0,6);
        this.numbers.sort(LottoNumber::compareTo);
    }

    //manual constructor
    public Lotto(List<Integer> numbers){
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        validateLottoNumbers(lottoNumbers);
        this.numbers = lottoNumbers; // 검증 후 할당
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != 6){
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if(numbers.stream().distinct().count() != 6){
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    //로또 번호는 불변하므로 setter를 제공하지 않습니다.
    public List<LottoNumber> getNumbers(){
        return numbers;
    }

    public int matchCount(WinningNumbers winningNumbers){
        int matchCount = 0;
        for(LottoNumber number : numbers){
            matchCount = countMatches(winningNumbers, number.getNumber(), matchCount);
        }
        return matchCount;
    }

    public boolean checkHasBonus(int bonusNumber){
        return numbers.contains(new LottoNumber(bonusNumber));
    }

    private static int countMatches(WinningNumbers winningNumbers, int number, int matchCount) {
        if(winningNumbers.getWinningNumbers().contains(number)){
            matchCount++;
        }
        return matchCount;
    }

    @Override
    public String toString() {
        return this.getNumbers().toString();
    }
}