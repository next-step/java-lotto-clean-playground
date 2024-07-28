package view;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    private static final String DELIMITER_OF_LOTTOS_STATUS = "\n";
    private static final String PREFIX_OF_LOTTO_STATUS = "[";
    private static final String DELIMITER_OF_LOTTO_STATUS = ", ";
    private static final String SUFFIX_OF_LOTTO_STATUS = "]";

    public void printInputPurchasePriceGuide() {
        System.out.println("구입 금액을 입력해 주세요.");
    }

    public void printNumberOfLotto(int numberOfLotto) {
        printEmptyLine();
        System.out.printf("%d개를 구매했습니다.%n", numberOfLotto);
    }

    public void printStatusOfLottos(List<List<Integer>> statusOfLottos) {
        StringJoiner joiner = new StringJoiner(DELIMITER_OF_LOTTOS_STATUS);
        for (List<Integer> lottoNumbers : statusOfLottos) {
            joiner.add(getStatusOfLotto(lottoNumbers));
        }
        System.out.println(joiner);
    }

    private String getStatusOfLotto(List<Integer> lottoNumbers) {
        StringJoiner joiner =
            new StringJoiner(DELIMITER_OF_LOTTO_STATUS, PREFIX_OF_LOTTO_STATUS, SUFFIX_OF_LOTTO_STATUS);
        for (Integer lottoNumber : lottoNumbers) {
            joiner.add(lottoNumber.toString());
        }
        return joiner.toString();
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

    public void printLottoResult(int scoreCutoff, int prizeMoney, boolean isBonusBallMatching, int count) {
        System.out.println(getLottoResultContent(scoreCutoff, prizeMoney, isBonusBallMatching, count));
    }

    private String getLottoResultContent(int scoreCutoff, int prizeMoney, boolean isBonusBallMatching, int count) {
        if (isBonusBallMatching) {
           return String.format("%d개 일치, 보너스 볼 일치(%d원)- %d개", scoreCutoff, prizeMoney, count);
        }
        return String.format("%d개 일치 (%d원)- %d개", scoreCutoff, prizeMoney, count);
    }

    public void printROI(double roi) {
        System.out.printf("총 수익률은 %.2f입니다.%n", roi);
    }

    public void printInputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printInputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요");
    }

    public void printInputManualLottoNumber() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
