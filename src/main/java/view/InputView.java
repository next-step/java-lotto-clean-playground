package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.Price;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public Price inputPrice() {
        System.out.println("구입 금액을 입력해 주세요.");
        return new Price(Integer.parseInt(scanner.nextLine()));
    }

    public LottoTicket getWinnerTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new LottoTicket(
                Arrays.stream(scanner.nextLine().split(", "))
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
    }
}
