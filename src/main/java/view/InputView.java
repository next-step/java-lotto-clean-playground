package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError("InputView는 인스턴스화 할 수 없습니다.");
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해주세요.");
        int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        String input = scanner.nextLine();
        return Arrays.stream(input.split(","))
            .map(String::strip)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

}
