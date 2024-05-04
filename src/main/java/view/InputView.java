package view;

import java.util.Scanner;


public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int Input() {

        System.out.println("구입금액을 입력해 주세요.");
        String inputText = scanner.nextLine();

        int inputMoney = validText(inputText);

        return inputMoney;

    }

    public static int validText(String inputText) {

        int inputMoney = 0;

        try {
            inputMoney = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력 가능합니다.");
            throw e;
        }

        return inputMoney;
    }

}
