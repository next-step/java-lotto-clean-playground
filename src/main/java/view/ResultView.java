package view;

import domain.Lotto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매하셨습니다.");
        for (Lotto lotto : lottos) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]")));
    }
}
