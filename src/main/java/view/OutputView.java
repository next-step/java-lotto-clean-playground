package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public void printPurchasedTicketsMessage(int manualTickets, int autoTickets) {
        System.out.println();
        String message = String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualTickets, autoTickets);
        System.out.println(message);
    }

    public void printPurchaseMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printTargetNumberMessage() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printManualCountMessage() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public void printManualNumberMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public void printBonusNumberMessage() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public void printStatusMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printResultMessage(int correctNumber, int price, int count) {
        String message = String.format("%d개 일치 (%d원) - %d개%n", correctNumber, price, count);
        System.out.println(message);
    }

    public void printBonusResultMessage(int correctNumber, int price, int count) {
        String message = String.format("%d개 일치, 보너스 볼 일치 (%d원) - %d개%n", correctNumber, price, count);
        System.out.println(message);
    }

    public void printTotalBenefitResultMessage(double totalBenefit) {
        String message = String.format("총 수익률은 %.2f입니다.", totalBenefit);
        System.out.println(message);
    }

    public void printLottoNumbers(List<Lotto> lottoTickets) {
        for (Lotto ticket : lottoTickets) {
            System.out.println(ticket.getNumbers());
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }
}
