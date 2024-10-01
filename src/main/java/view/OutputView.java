package view;

import domain.Lotto;
import domain.Lottos;

import java.util.Map;

public class OutputView {

    public static void printNumberOfLottos(int numberOfLottos) {
        System.out.println(numberOfLottos + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos){ // 로또 번호 출력 메서드
        for (Lotto lotto : lottos.getLottos()){
            System.out.println(lotto.getLottoNumbers().toString());
        }
    }

    public static void printWinningStatistics(Map<Integer, Integer> matchCounts, int prize3, int prize4, int prize5, int prize6) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        matchCounts.forEach((matches, count) -> {
            switch (matches) {
                case 3:
                    System.out.println(matches + "개 일치 (" + prize3 + "원)- " + count + "개");
                    break;
                case 4:
                    System.out.println(matches + "개 일치 (" + prize4 + "원)- " + count + "개");
                    break;
                case 5:
                    System.out.println(matches + "개 일치 (" + prize5 + "원)- " + count + "개");
                    break;
                case 6:
                    System.out.println(matches + "개 일치 (" + prize6 + "원)- " + count + "개");
                    break;
            }
        });
    }

    public static void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)", returnRate);
    }

}
