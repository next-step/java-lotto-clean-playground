package view;

import domain.LottoNumber;

import java.util.List;

public class ResultView {

    public static void printPurchase(List<List<LottoNumber>> lottos, int passiveCount, int autoCount) {
        System.out.println("수동으로 " + passiveCount + "장, 자동으로 " + autoCount + "개를 구매했습니다."
        );
        for (List<LottoNumber> lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printResult(List<Integer> counts, double rate) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        int[] result = new int[8];

        for (int count : counts) {
            result[count]++;
        }

        System.out.println("3개 일치 (5,000원) - " + result[3] + "개");
        System.out.println("4개 일치 (50,000원) - " + result[4] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result[5] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result[7] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result[6] + "개");
        System.out.printf("총 수익률은 %.2f입니다.\n", rate);
    }
}
