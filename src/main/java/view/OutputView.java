package view;

import model.Lotto;
import model.Lottos;

import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void showLotto(final Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.%n", lottos.getBuyLottoCount());

        for (Lotto lotto : lottos.getLottos()) {
            final String lottoNumber = lotto.getNumbers().stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(DELIMITER));

            System.out.printf("[%s]%n", lottoNumber);
        }
    }
}
