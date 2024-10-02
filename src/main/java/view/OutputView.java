package view;

import domain.Lotto;
import domain.Prize;

import java.util.List;
import java.util.Map;

public class OutputView {

    //구입한 로또 개수
    public static void printNumberOfLottos(int numberOfHandLottos, int numberOfLottos) {
        System.out.println("수동으로 " + numberOfHandLottos + "장, 자동으로 " + numberOfLottos + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) { // 로또 번호 출력 메서드
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getLottoNumbers().toString());
        }
    }

    public static void printWinningStatistics(Map<Prize, Integer> matchCounts) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Prize prize : Prize.values()) {
            if (prize == Prize.FIVE_MATCHES_BONUS) {
                System.out.println("5개 일치, 보너스 볼 일치(" + prize.getPrizeAmount() + "원)- " + matchCounts.get(prize) + "개");
            }
            //else 쓰는게 맞는 것 같지만 조건때문에 if문으로 변경
            if (prize != Prize.FIVE_MATCHES_BONUS) {
                System.out.println(prize.getMatchingCount() + "개 일치 (" + prize.getPrizeAmount() + "원)- " + matchCounts.get(prize) + "개");
            }
        }
    }

    public static void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)", returnRate);
    }
}
