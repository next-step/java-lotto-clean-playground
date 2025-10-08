package Model;

import java.util.Collections;
import java.util.List;

public class Lotto {
    protected List<Integer> numbers;

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
