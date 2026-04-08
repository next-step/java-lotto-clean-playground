package view;

import domain.Rank;
import java.util.List;

public class ResultView {
    public void printPurchaseCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printLottoNumbers(List<Integer> numbers) {
        System.out.println(numbers);
    }

    public void printStatics() {
        System.out.println("\n당첨 통계\n---------");
    }

    public void printWinningStatics(Rank rank, int count) {
        if (rank == Rank.SECOND) {
            System.out.println("5개 일치, 보너스 볼 일치(" + rank.getPrizemoney() + "원) - " + count + "개");
        }
        if (rank != Rank.SECOND) {
            System.out.println(rank.getMatchnumbers() + "개 일치 (" + rank.getPrizemoney() + "원)- " + count + "개");
        }
    }

    public void printYield(double yield, boolean sign) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
        if (sign) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라 더 뽑아도 된다는 의미임)");
        } else {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}