package view;

import domain.Lotto;
import domain.Lottos;

import java.util.List;

public class ResultView {

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.\n");

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers());
        }
    }

}
