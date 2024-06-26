package view;

import model.Lotto;
import model.Lottos;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER = "\n";

    public static void showLotto(final Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.%n", lottos.getBuyLottoCount());

        StringJoiner stringJoiner = new StringJoiner(DELIMITER);

        for (Lotto lotto : lottos.getLottos()) {
            final List<Integer> numbers = lotto.getNumbers();
            stringJoiner.add(numbers.toString());
        }

        System.out.println(stringJoiner);
    }
}
