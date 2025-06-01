package view;

import java.util.ArrayList;
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

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static int howManyTimeBuyHandTicket() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static List<String> writeHandTickets(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine().strip();
            if (input.isBlank()) {
                throw new IllegalArgumentException("로또 번호 입력은 비어 있을 수 없습니다.");
            }
            inputs.add(input);
        }
        return inputs;
    }
}
