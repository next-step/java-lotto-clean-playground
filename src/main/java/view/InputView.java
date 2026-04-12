package view;

import domain.LottoNumber;
import domain.LottoTicket;
import domain.ManualTicketCount;
import domain.Price;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public ManualTicketCount inputManualTicketCount(Price price) {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return new ManualTicketCount(Integer.parseInt(scanner.nextLine()), price);
    }

    public List<List<Integer>> inputManualTickets(ManualTicketCount manualTicketCount) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualTickets = new ArrayList<>();
        for (int i = 0; i < manualTicketCount.getCount(); i++) {
            manualTickets.add(
                    Arrays.stream(scanner.nextLine().split(", "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        }
        return manualTickets;
    }

    public LottoTicket inputWinnerTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new LottoTicket(
                Arrays.stream(scanner.nextLine().split(", "))
                        .map(Integer::parseInt)
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toList())
        );
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return LottoNumber.valueOf(Integer.parseInt(scanner.nextLine()));
    }
}
