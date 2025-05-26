package view;

import domain.Lotto;
import domain.LottoList;

public class OutputView {
    public static void printLottoAmount(int cnt) {
        System.out.println(cnt + "개를 구매했습니다.");
    }

    public static void printLottoLists(LottoList lottoList) {
        for (Lotto list : lottoList.getLottoLists()) {
            System.out.println(list);
        }
    }
}
