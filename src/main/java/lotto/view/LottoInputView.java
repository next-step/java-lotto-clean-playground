package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.model.BonusBall;
import lotto.model.LottoNumbers;
import lotto.model.Money;

public class LottoInputView {

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                return new Money(scanner.nextInt());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LottoNumbers inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                List<Integer> numbers = parseWinningNumbers(scanner.nextLine());
                return new LottoNumbers(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public BonusBall inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                return new BonusBall(scanner.nextInt());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int inputManualCount(long totalMoney) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                int count = scanner.nextInt();
                if (count * 1000L > totalMoney) {
                    System.out.println("수동 구매 수가 구입 금액을 초과할 수 없습니다.");
                    continue;
                }
                return count;
            } catch (NumberFormatException e) {
                System.out.println("정수를 입력해주세요.");
            }
        }
    }

    public List<LottoNumbers> inputManualNumbers(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoNumbers> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(inputSingleManualNumbers());
        }
        return manualNumbers;
    }

    private LottoNumbers inputSingleManualNumbers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                List<Integer> numbers = parseWinningNumbers(scanner.nextLine());
                return new LottoNumbers(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
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
}
