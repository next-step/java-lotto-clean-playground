package model.lotto;

import java.util.List;
import java.util.TreeSet;

public class AutoLotto extends Lotto {

    private AutoLotto(TreeSet<Integer> numbers) {
        super(numbers);
    }

    public static AutoLotto of(List<Integer> numbers) {
        return new AutoLotto(new TreeSet<>(numbers));
    }
}
