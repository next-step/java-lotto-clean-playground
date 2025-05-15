package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoManage {
    public static List<Integer> shuffleNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(0, 6));
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

}
