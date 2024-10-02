package view;

import domain.Lotto;
import domain.Lottos;
import domain.Prize;

import java.util.Map;

public class OutputView {

    //구입한 로또 개수
    public static void printNumberOfLottos(int numberOfLottos) {
        System.out.println(numberOfLottos + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos){ // 로또 번호 출력 메서드
        for (Lotto lotto : lottos.getLottos()){
            System.out.println(lotto.getLottoNumbers().toString());
        }
    }

    public static void printWinningStatistics(Map<Prize, Integer> matchCounts) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Prize prize: Prize.values()){
            if (prize == Prize.FIVE_MATCHES_BONUS){
                System.out.println("5개 일치, 보너스 볼 일치(" + prize.getPrizeAmount() + "원)- " + matchCounts.get(prize) + "개");
            }
            //else 쓰는게 효율적일 것 같지만 조건때문에
            if (prize != Prize.FIVE_MATCHES_BONUS) {
                System.out.println(prize.getMatchingCount() + "개 일치 (" + prize.getPrizeAmount() + "원)- " + matchCounts.get(prize) + "개");
            }
        }
    }

    public static void printReturnRate(double returnRate) {
        System.out.printf("총 수익률은 %.2f입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)", returnRate);
    }

}
