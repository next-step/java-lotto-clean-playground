package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.wrappers.LottoPayment;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public LottoPayment readLottoPayment() {
        System.out.println("구입 금액을 입력해 주세요.");
        LottoPayment payment = new LottoPayment(scanner.nextInt());
        scanner.nextLine();
        return payment;
    }

    public LottoTicket readWinnerTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return new LottoTicket(
                Arrays.stream(scanner.nextLine().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .toList()
        );
    }
}
