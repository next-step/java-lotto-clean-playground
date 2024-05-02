package view;


import model.Lotto;

import java.util.ArrayList;

public class OutView {

    public static void PurchaseRecord(ArrayList<Lotto> haveLottos) {

        System.out.println("14개를 구매했습니다.");

        for (int i = 0; i < haveLottos.size(); i++) {
            LottoInfo(haveLottos.get(i).getLottoNumber());
        }
    }

    public static void LottoInfo(ArrayList<Integer> lottoNumber) {

        System.out.print("[");

        for (int i = 0; i < 5; i++) {
            System.out.print(lottoNumber.get(i) + ", ");
        }

        System.out.println(lottoNumber.get(5) + "]");
    }
}
