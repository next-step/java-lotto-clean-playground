package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {
    private static final int MAX_NUMBER=45;
    private static final int MIN_NUMBER=1;
    private static final int LOTTO_SIZE=6;

    private final List<Integer> seedNumbers;

    public RandomLottoGenerator(){
        this.seedNumbers = initializeNumbers();
    }

    private List<Integer> initializeNumbers(){
        List<Integer>  numbers = new ArrayList<>();
        for(int i = MIN_NUMBER;i<=MAX_NUMBER;i++){
            numbers.add(i);
        }
        return numbers;
    }

    public List<Integer> generate(){
        Collections.shuffle(seedNumbers);
        List<Integer> LottoNumbers = new ArrayList<>(seedNumbers.subList(0,LOTTO_SIZE));
        Collections.sort(LottoNumbers);

        return LottoNumbers;
    }
}
