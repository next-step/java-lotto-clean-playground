package lottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator{

    @Override
    public List<Integer> generateLotto() {
        List<Integer> numbers = createNumbers();
        Collections.shuffle(numbers);
        List<Integer> lotto = numbers.subList(0, 6);
        Collections.sort(lotto);
        return lotto;
    }

    private List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int count = 1; count <= 45; count++) {
            numbers.add(count);
        }
        return numbers;
    }
}
