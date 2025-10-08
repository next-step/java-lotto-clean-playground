package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoLotto extends Lotto {

    public AutoLotto() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        this.numbers = new ArrayList<>(numbers.subList(0, 6));

        Collections.sort(this.numbers);
    }

}
