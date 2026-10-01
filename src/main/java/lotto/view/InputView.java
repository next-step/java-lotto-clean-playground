package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    // 사용자에게 구입금액을 입력받는 메서드
    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    // 사용자에게 로또 번호를 입력받는 메서드
    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    private static List<Integer> parseNumbers(String input) {
        List<Integer> numbers = new ArrayList<>();
        for (String number : input.split(",")) {
            numbers.add(parseNumber(number));
        }
        return numbers;
    }

    private static int parseNumber(String number) {
        return Integer.parseInt(number.trim());
    }
}
