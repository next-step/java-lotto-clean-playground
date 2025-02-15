package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Lotto를 관리하는 일급 컬렉션
public class Lotto {
    private final List<Long> numbers;

    //automated constructor
    public Lotto() {
        List<Long> cardinateNumbers = new ArrayList<>();
        for(long i=1; i<=45; i++){
            cardinateNumbers.add(i);
        }
        Collections.shuffle(cardinateNumbers);
        this.numbers = cardinateNumbers.subList(0,6);
        this.numbers.sort(Long::compareTo);
    }

    //로또 번호는 불변하므로 setter를 제공하지 않습니다.
    public List<Long> getNumbers(){
        return numbers;
    }

    public long matchCount(WinningNumbers winningNumbers){
        long matchCount = 0;
        for(Long number : numbers){
            matchCount = countMatches(winningNumbers, number, matchCount);
        }
        return matchCount;
    }

    private static long countMatches(WinningNumbers winningNumbers, Long number, long matchCount) {
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