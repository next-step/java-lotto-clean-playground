package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<Integer> getNumbers(){
        return numbers;
    }

    @Override
    public String toString() {
        return this.getNumbers().toString();
    }
}
