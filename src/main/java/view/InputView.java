package view;

import domain.LottoNumber;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.print("구입금액을 입력해 주세요.\n");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<LottoNumber> getWinningNumbers() {
        System.out.print("지난 주 당첨 번호를 입력해 주세요.\n");
        return Stream.of(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
