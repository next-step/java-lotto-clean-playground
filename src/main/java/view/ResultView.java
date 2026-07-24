package view;

import java.util.List;

public class ResultView {
    public static void printLottoResult(List<List<Integer>> purchasedLottoNumbers
    ){
        System.out.println(purchasedLottoNumbers.size() + "개를 구매했습니다.");
        for (List<Integer> purchasedLottoNumber : purchasedLottoNumbers) {
            System.out.println(purchasedLottoNumber);
        }
    }
}
