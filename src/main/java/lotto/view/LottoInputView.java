package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.model.Money;

public class LottoInputView {

    private final Scanner scanner = new Scanner(System.in);

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return readMoney();
    }

    private int readMoney() {
        while (true) {
            try {
                int money = Integer.parseInt(scanner.nextLine());
                new Money(money);
                return money;
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력할 수 있습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while (true) {
            List<Integer> numbers = parseWinningNumbers(scanner.nextLine());
            if (numbers.size() == 6) {
                return numbers;
            }
            System.out.println("6개의 숫자를 입력해주세요.");
        }
    }

    private List<Integer> parseWinningNumbers(String input) {
        String[] split = input.split(",");
        List<Integer> result = new ArrayList<>();
        for (String num : split) {
            result.add(Integer.parseInt(num.trim()));
        }
        return result;
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return readBonusNumber();
    }

    private int readBonusNumber() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.matches("\\d+")) {
                System.out.println("숫자만 입력할 수 있습니다.");
                continue;
            }
            int bonusBall = Integer.parseInt(input);
            if (bonusBall < 1 || bonusBall > 45) {
                System.out.println("1에서 45 사이의 숫자만 입력할 수 있습니다.");
                continue;
            }
            return bonusBall;
        }
    }

    public int inputManualCount(int totalMoney) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        while (true) {
            int count = Integer.parseInt(scanner.nextLine());
            if (count * 1000 <= totalMoney) {
                return count;
            }
            System.out.println("수동 구매 수가 구입 금액을 초과할 수 없습니다.");
        }
    }

    public List<List<Integer>> inputManualNumbers(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(inputSingleManualNumbers());
        }
        return manualNumbers;
    }

    private List<Integer> inputSingleManualNumbers() {
        while (true) {
            List<Integer> numbers = parseWinningNumbers(scanner.nextLine());
            if (numbers.size() == 6) {
                return numbers;
            }
            System.out.println("6개의 숫자를 입력해주세요.");
        }
    }
}
