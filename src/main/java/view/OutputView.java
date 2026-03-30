package view;

import java.util.List;

public class OutputView {
    public void printStartGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printQuantity(int num) {
        System.out.println("\n" + num + "개를 구매했습니다.");
    }

    public void printElements(List<String> elements) {
        elements.forEach(System.out::println);
    }

    public void printPrompt() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printStatisticHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    public void printStatistics(int num1, int num2, int num3) {
        System.out.printf("%d개 일치 (%d원)- %d개\n", num1, num2, num3);
    }

    public void printResult(double value) {
        String result = String.format("총 수익율은 %.2f입니다.", value);
        String status = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        if (value >= 1) {
            status = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
        }
        System.out.println(result + status);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
