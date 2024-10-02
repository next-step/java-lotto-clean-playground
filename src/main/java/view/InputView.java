package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputToBuyLotto() {
        System.out.println("구입금액을 입력해주세요.");
        final int money = scanner.nextInt();
        scanner.nextLine();
        return money;
    }

    public static int inputToBuyManualLotto() {
        System.out.println("\n수동으로 구매 로또 수 입력해 주세요.");
        final int moneyToButManualLotto = scanner.nextInt();
        scanner.nextLine();
        return moneyToButManualLotto;
    }

    public static List<List<Integer>> inputToManualLotto(int numberOfManualLottos) {
        List<List<Integer>> manualLottoInputs = new ArrayList<>();
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

        for(int i = 0; i < numberOfManualLottos; i++) {
            String input = scanner.nextLine(); //8, 21, 23, 41, 42, 43
            List<Integer> lottoNumbers = new ArrayList<>();
            for (String number : input.split(", ")) {
                lottoNumbers.add(Integer.parseInt(number.trim())); // Trim spaces and parse to Integer
            }
            manualLottoInputs.add(lottoNumbers); // Add the list of integers to the result
        }
        return manualLottoInputs;
    }

    public static List<Integer> lastWeekWinningNumber() {
        List<Integer> lastWeekWinningNumbers = new ArrayList<>();
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");

        String input = scanner.nextLine();
        StringBuilder numberBuffer = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch)) {
                numberBuffer.append(ch);
            }
            if (ch == ',' || ch == ' ') {
                if (!numberBuffer.isEmpty()) {
                    lastWeekWinningNumbers.add(Integer.parseInt(numberBuffer.toString()));
                    numberBuffer.setLength(0);
                }
            }
        }
        if (!numberBuffer.isEmpty()) {
            lastWeekWinningNumbers.add(Integer.parseInt(numberBuffer.toString()));
        }

        validateWinningNumbersUnderSix(lastWeekWinningNumbers);
        validateAllNumbersValue(lastWeekWinningNumbers);

        return lastWeekWinningNumbers;
    }

    public static int bonusWinningNumber() {
        System.out.println("\n보너스 볼을 입력해주세요.");
        int bonusWinningNumber = scanner.nextInt();
        scanner.nextLine();
        return bonusWinningNumber;
    }

    private static void validateWinningNumbersUnderSix(final List<Integer> lastWeekWinningNumber) {
        if (lastWeekWinningNumber.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private static void validateAllNumbersValue(final List<Integer> lastWeekWinningNumber) {
        for (int number : lastWeekWinningNumber) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("번호는 1부터 45 사이여야 합니다.");
            }
        }
    }
}
