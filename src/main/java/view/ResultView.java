package view;

import domain.LottoNumber;
import domain.Lottos;
import domain.PurchaseManage;

public class ResultView {
    public static void showNum(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.", lottos.size());
        System.out.println();
        for (LottoNumber lottoNumber : lottos.getLottos()) {
            System.out.println(lottoNumber.getLottoNumbers());
        }
    }
}
