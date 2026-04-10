package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return validateNumberFormat(input);
    }

    public int getManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        return validateNumberFormat(input);
    }


    public void printManualInputMassage() {
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
    }

    public String readManualInput() {
        return scanner.nextLine();
    }

    public List<Integer> getResult() {
        List<Integer> result = new ArrayList<>();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine().trim();
        validateDelimiter(input);
        String[] splitInput = input.split(",");
        for (String number : splitInput) {
            result.add(parseInt(number));
        }
        return result;
    }


    public int getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();

        return validateNumberFormat(input);
    }


    private int validateNumberFormat(String input) {
        try {
            return parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("[Error]숫자만 입력 가능합니다.");
        }
    }


    private void validateDelimiter(String input) {
        if (!input.matches("\\d+(,\\s*\\d+)*")) {
            throw new IllegalArgumentException("Error 구분자는 ','만 가능합니다");
        }
    }

}
