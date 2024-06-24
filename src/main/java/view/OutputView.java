package view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER_OF_LOTTOS_STATUS = "\n";

    public void printInputPriceGuide() {
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

}
