package view;

import dto.LottoDTO;

import java.util.List;


public class OutputView {

    public static void printResultOfBuying(int numberOfManualLottos, int numberOfAutoLottos) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", numberOfManualLottos, numberOfAutoLottos);
    }

    public static void printResultOfAutoLotto(List<List<Integer>> lottoCollection) {
        for (List<Integer> lotto : lottoCollection) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printLottoWinningStatistics(LottoDTO lottoDTO) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + lottoDTO.correct3 + "개");
        System.out.println("4개 일치 (50000원)- " + lottoDTO.correct4 + "개");
        System.out.println("5개 일치 (1500000원)- " + lottoDTO.correct5 + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)- " + lottoDTO.correct5WithBonus + "개");
        System.out.println("6개 일치 (2000000000원)- " + lottoDTO.correct6 + "개");
        System.out.printf("총 수익률은 %.2f입니다.", lottoDTO.income);
    }
}
