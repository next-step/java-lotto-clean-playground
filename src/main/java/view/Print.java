package view;

import java.util.ArrayList;

public class Print {

    // 1~6개 일치하는 갯수
    public void printPurchasedLottoCount(ArrayList<ArrayList<Integer>> totalLottos) {
        System.out.printf("\n%d개를 구매했습니다.\n", totalLottos.size());
        for (ArrayList<Integer> totalLotto : totalLottos) {
            System.out.println(totalLotto);
        }

        // for
//        System.out.printf("%d개 일치 (%d원)- %d개", );
    }

    public void printWinningCount(ArrayList<Integer> winningCount, double rate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        System.out.printf("3개 일치 (5000원)- %d개\n", winningCount.get(0));
        System.out.printf("4개 일치 (50000원)- %d개\n", winningCount.get(1));
        System.out.printf("5개 일치 (1500000원)- %d개\n", winningCount.get(2));
        System.out.printf("6개 일치 (2000000000원)- %d개\n", winningCount.get(3));

        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n", rate);

    }

}
