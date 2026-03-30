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

    public void printResult(String result) {
        System.out.println(result);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
