package view;

import domain.WinningNumbers;

import java.util.ArrayList;

public class Print {

    // 1~6개 일치하는 갯수
    public void printPurchasedLottoCount(int manualCount, ArrayList<ArrayList<Integer>> totalLottos) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, totalLottos.size() - manualCount);
        for (ArrayList<Integer> totalLotto : totalLottos) {
            System.out.println(totalLotto);
        }

        // for
//        System.out.printf("%d개 일치 (%d원)- %d개", );
    }

    public void printWinningCount(ArrayList<Integer> winningCount, double rate) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        System.out.printf("3개 일치 (%d원)- %d개\n", WinningNumbers.findByIndex(0), winningCount.get(0));
        System.out.printf("4개 일치 (%d원)- %d개\n", WinningNumbers.findByIndex(1), winningCount.get(1));
        System.out.printf("5개 일치 (%d원)- %d개\n", WinningNumbers.findByIndex(2), winningCount.get(2));
        System.out.printf("5개 일치, 보너스 볼 일치(%d원) - %d개\n", WinningNumbers.findByIndex(3), winningCount.get(3));
        System.out.printf("6개 일치 (%d원)- %d개\n", WinningNumbers.findByIndex(4), winningCount.get(4));

        System.out.printf("총 수익률은 %.2f입니다.\n", rate);

    }

}
