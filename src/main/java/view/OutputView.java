package view;

import domain.LottoResult;
import domain.Rank;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER_OF_LOTTOS_STATUS = "\n";

    public void printInputPurchasePriceGuide() {
        System.out.println("구입 금액을 입력해 주세요.");
    }

    public void printNumberOfLotto(int numberOfLotto) {
        printEmptyLine();
        System.out.printf("%d개를 구매했습니다.%n", numberOfLotto);
    }

    public void printStatusOfLottos(List<String> statusOfLottos) {
        StringJoiner joiner = new StringJoiner(DELIMITER_OF_LOTTOS_STATUS);
        for (String statusOfLotto : statusOfLottos) {
            joiner.add(statusOfLotto);
        }
        System.out.println(joiner);
    }

    private void printEmptyLine() {
        System.out.println();
    }

    public void printInputWinningNumbers() {
        printEmptyLine();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }


    public void printLottoResultStart() {
        printEmptyLine();
        System.out.println("당첨 통계\n---------");
    }

    public void printLottoResult(int scoreCutoff, int prizeMoney, int count) {
        System.out.printf("%d개 일치 (%d원)- %d개%n", scoreCutoff, prizeMoney, count);
    }

    public void printROI(double roi) {
        System.out.printf("총 수익률은 %.2f입니다.%n", roi);
    }

}
