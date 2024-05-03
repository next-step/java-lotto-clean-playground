package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    public String readLottoPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public List<String> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = SCANNER.nextLine();
        return Stream.of(input.split(","))
                .filter(it -> !it.isBlank())
                .toList();
    }
}
