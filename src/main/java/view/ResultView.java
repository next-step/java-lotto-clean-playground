package view;

import java.util.List;

public class ResultView {

    public static void printLottoCount(int count) {
        System.out.println();
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottoNumbers(List<Integer> lottoNumbers) {
        System.out.println(lottoNumbers);
    }
}
