package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private List<Integer> lotto;

    public Lotto() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);
        this.lotto = new ArrayList<>(numbers.subList(0, 6));

        Collections.sort(this.lotto); // 뽑은 6개의 숫자를 오름차순으로 정렬
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
