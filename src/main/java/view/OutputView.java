package view;

import model.Lotto;
import model.LottoList;
import model.WinningType;

import java.util.List;

public class OutputView {

    private final static String BUY_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private final static String COUNT_SUFFIX = "개";


    public void printTicketCount (int autoCount, int manualCount){
        System.out.println();
        String message = String.format(BUY_MESSAGE, manualCount, autoCount);
        System.out.println(message);    }
    public void printLottoNumbers(LottoList lottoList) {
        List<Lotto> lottos = lottoList.getLottoList();
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printResults(int [] arr, double rate){
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(WinningType.FIFTH.getSameCountMessage() + arr[0] + COUNT_SUFFIX);
        System.out.println(WinningType.FOURTH.getSameCountMessage() + arr[1] + COUNT_SUFFIX);
        System.out.println(WinningType.THIRD.getSameCountMessage() + arr[2] + COUNT_SUFFIX);
        System.out.println(WinningType.SECOND.getSameCountMessage() + arr[3] + COUNT_SUFFIX);
        System.out.println(WinningType.FIRST.getSameCountMessage() + arr[4] + COUNT_SUFFIX);
        System.out.printf("총 수익률은 %.2f 입니다. (기준이 1이기 때문에 결과적으로 손해라는 의미임)%n", rate);
    }
}
