package view;

import domain.Lotto;
import domain.LottoNumber;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printPurchaseCount(int count) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(
                    lotto.getNumbers().stream()
                            .map(LottoNumber::number)
                            .toList()
            );
        }
    }
}
