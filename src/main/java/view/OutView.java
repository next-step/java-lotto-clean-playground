package view;


import model.Lotto;

import java.util.ArrayList;
import java.util.List;

public class OutView {

    public static void PurchaseRecord(List<Lotto> haveLottos) {


        System.out.println("\n" + haveLottos.size() + "개를 구매했습니다.");

        for (int i = 0; i < haveLottos.size(); i++) {
            LottoInfo(haveLottos.get(i).getLottoNumber());
        }
    }

    public static void LottoInfo(List<Integer> lottoNumber) {

        System.out.print("[");

        for (int i = 0; i < 5; i++) {
            System.out.print(lottoNumber.get(i) + ", ");
        }

        System.out.println(lottoNumber.get(5) + "]");
    }
}
