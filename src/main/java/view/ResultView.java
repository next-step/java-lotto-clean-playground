package view;

import domain.Lotto;
import domain.Numbers;
import java.util.Collections;

public class ResultView {
    private static final String PURCHASED_COUNT = "%d개를 구매했습니다.%n";
    public void printLottoCount(int count) {
        System.out.printf(PURCHASED_COUNT, count);
    }

    public void printLottoNumbers(Lotto lotto) {
        Numbers numbers = lotto.getNumbers();
        Collections.sort(numbers.getNumbers());
        System.out.println(numbers);
    }
}
