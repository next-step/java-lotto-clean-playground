package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {

        System.out.println("구입금액을 입력해 주세요.");
        String inputText = scanner.nextLine();

        int inputMoney = validText(inputText);

        return inputMoney;
    }

    public static int validText(final String inputText) {

        int inputMoney = 0;

        try {
            inputMoney = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력 가능합니다.");
            throw e;
        }

        return inputMoney;
    }

    public static List<Integer> inputCollectedNumber() {

        System.out.println('\n' + "지난 주 당첨 번호를 입력해 주세요.");
        String number = scanner.nextLine();

        List<Integer> numbers = validCollectedText(number);

        return numbers;
    }

    public static List<Integer> validCollectedText(final String number) {

        List<Integer> numbers = new ArrayList<>();

        try {
            numbers = Arrays.stream(number.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 형식에 맞게 다시 입력해주세요");
            throw e;
        }

        return numbers;
    }
}
