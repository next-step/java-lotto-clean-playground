package view;

import domain.Lotto;
import java.util.Collections;
import java.util.List;

public class ResultView {
    private static final String PURCHASED_COUNT = "%d개를 구매했습니다.%n";
    public void printLottoCount(int count) {
        System.out.printf(PURCHASED_COUNT, count);
    }

    public void printLottoNumbers(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        Collections.sort(numbers);
        System.out.println(numbers);
    }
}
