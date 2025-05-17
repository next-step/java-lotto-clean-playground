package view;

import domain.Lotto;
import java.util.Collections;
import java.util.List;

public class ResultView {
    public void printLottoCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLottoNumbers(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        Collections.sort(numbers);
        System.out.println(numbers);
    }
}
