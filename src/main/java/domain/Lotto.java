package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Lotto를 관리하는 일급 컬렉션
public class Lotto {
    private final List<Integer> numbers;

    //automated constructor
    public Lotto() {
        List<Integer> cardinateNumbers = new ArrayList<>();
        for(int i=1; i<=45; i++){
            cardinateNumbers.add(i);
        }
        Collections.shuffle(cardinateNumbers);
        this.numbers = cardinateNumbers.subList(0,6);
        this.numbers.sort(Integer::compareTo);
    }

    //로또 번호는 불변하므로 setter를 제공하지 않습니다.
    public List<Integer> getNumbers(){
        return numbers;
    }

    public int matchCount(WinningNumbers winningNumbers){
        int matchCount = 0;
        for(Integer number : numbers){
            matchCount = countMatches(winningNumbers, number, matchCount);
        }
        return matchCount;
    }

    private static int countMatches(WinningNumbers winningNumbers, Integer number, int matchCount) {
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