package view;

import domain.LottoRank;
import dto.LottoStatistics;
import dto.LottoStatus;

import java.math.BigDecimal;
import java.util.List;

public class OutputView {
    public void printInputStartGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printInputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해주세요.");
    }

    public void printInputManualNumbers() {
        System.out.println("\n수동으로 구매할 번호를 입력해주세요.");
    }

    public void printQuantity(int num1, int num2) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", num1, num2);
    }

    public void printElements(List<LottoStatus> lottoStatuses) {
        lottoStatuses.stream()
                .map(LottoStatus::toString)
                .forEach(System.out::println);
    }

    public void printPrompt() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printBonusPrompt() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
    }

    public void printStatisticHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(LottoStatistics lottoStatistics) {
        lottoStatistics.matchedCount().entrySet().stream()
                .filter(entry -> entry.getKey() != LottoRank.MISS)
                .forEach(entry -> printMatchedResult(entry.getKey(), entry.getValue()));
    }

    public void printResult(BigDecimal value) {
        String result = String.format("총 수익율은 %.2f입니다.", value);
        String status = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        if (value.compareTo(BigDecimal.valueOf(1)) >= 1) {
            status = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        System.out.println(result + status);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    private void printMatchedResult(LottoRank lottoRank, int matchedCount) {
        String result = String.format("%d개 일치", lottoRank.getMatchCount());
        if (lottoRank.isMatchBonus()) {
            result += ", 보너스 볼 일치";
        }
        result += String.format("(%d원) - %d개", lottoRank.getPrice(), matchedCount);
        System.out.println(result);
    }
}
