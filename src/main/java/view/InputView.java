package view;

import domain.lotto.Ball;
import domain.lotto.Ticket;
import domain.lotto.WinnerBalls;
import domain.lotto.Payment;
import domain.lotto.TicketCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public Payment readPayment() {
        System.out.println("구입 금액을 입력해 주세요.");

        return new Payment(Integer.parseInt(scanner.nextLine()));
    }

    public WinnerBalls readWinnerBalls() {
        System.out.println();

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Ticket winnerTicket = readTicket();

        System.out.println();

        System.out.println("보너스 볼을 입력해주세요.");
        Ball bonusBall = readBall();

        return new WinnerBalls(winnerTicket, bonusBall);
    }

    public Ticket readTicket() {
        return new Ticket(Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(Ball::new)
                .toList());
    }

    private Ball readBall() {
        return new Ball(Integer.parseInt(scanner.nextLine()));
    }

    public TicketCount readManualTicketCount() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return readTicketCount();
    }

    private TicketCount readTicketCount() {
        return new TicketCount(Integer.parseInt(scanner.nextLine()));
    }

    public List<Ticket> readManualTickets(TicketCount manualTicketCount) {
        List<Ticket> tickets = new ArrayList<>();

        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < manualTicketCount.getValue(); i++) {
            tickets.add(readTicket());
        }

        return tickets;
    }
}
