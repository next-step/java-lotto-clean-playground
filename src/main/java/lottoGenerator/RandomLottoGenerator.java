package lottoGenerator;

import static domain.Lotto.LOTTO_SIZE;
import static domain.LottoNumber.MAX_NUMBER;
import static domain.LottoNumber.MIN_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator implements LottoGenerator{

    @Override
    public List<Integer> generateLotto() {
        List<Integer> numbers = createNumbers();
        Collections.shuffle(numbers);
        List<Integer> lotto = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(lotto);
        return lotto;
    }

    private List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int count = MIN_NUMBER; count <= MAX_NUMBER; count++) {
            numbers.add(count);
        }
        return numbers;
    }
}
