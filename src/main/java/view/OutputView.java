package view;

import domain.Lotto;
import domain.LottoHistory;

import java.util.*;

public class OutputView {
    public static void printLottoHistory(LottoHistory history) {
        System.out.println(history.size() + "개를 구매했습니다.");
        for (Lotto lotto : history.getLottos()) {
            System.out.println(lotto);
        }
    }
}
