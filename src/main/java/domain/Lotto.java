package domain;

import java.util.List;

public class Lotto extends Numbers {

    public Lotto(List<Integer> numbers) {
        super(numbers);
    }

    @Override
    protected String label() {
        return "로또 번호";
    }
}
