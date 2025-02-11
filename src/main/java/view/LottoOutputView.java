package view;

import java.util.List;

public class LottoOutputView {

    public void printRequestAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printBuyLottos(long lottoCount, List<List<Integer>> lottosNumbers) {
        System.out.println(lottoCount + "개를 구매했습니다.");

        for (List<Integer> lottoNumbers : lottosNumbers) {
            System.out.println(lottoNumbers);
        }
    }
}
