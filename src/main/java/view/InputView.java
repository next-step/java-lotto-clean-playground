package view;

import domain.lotto.Number;
import domain.lotto.Ticket;
import domain.lotto.WinnerTicketPair;
import domain.lotto.wrappers.Payment;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public Payment readLottoPayment() {
        System.out.println("구입 금액을 입력해 주세요.");
        Payment payment = new Payment(scanner.nextInt());
        scanner.nextLine();
        return payment;
    }

    public WinnerTicketPair readWinnerTicketPair() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        Ticket winnerTicket = new Ticket(Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(Number::new)
                .toList());

        System.out.println("보너스 볼을 입력해주세요.");

        Number bonusBall = new Number(Integer.parseInt(scanner.nextLine()));

        return new  WinnerTicketPair(winnerTicket, bonusBall);
    }
}
