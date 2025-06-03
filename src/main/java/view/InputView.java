package view;

import domain.Money;
import util.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static Money getPurchaseAmount() {
        System.out.println("구매 금액을 입력해주세요.");
        int amount = Integer.parseInt(sc.nextLine().trim());
        Validator.validateLottoPurchaseAmount(amount);

        return new Money(amount);
    }

    public static int getManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            String amount = sc.nextLine();
            return Integer.parseInt(amount.trim());
        } catch (NumberFormatException e) {
            System.out.println("숫자 외의 값이 입력됨");
            throw e;
        }
    }

    public static List<List<Integer>> getManualLottoNumbers(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해주세요");
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            List<Integer> numbers = Arrays.stream(sc.nextLine().split(","))
                                            .map(String::trim)
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());
            Validator.validateManualLottoNumberInRange(numbers);
            manualNumbers.add(numbers);
        }
        return manualNumbers;
    }

    public static List<Integer> getLastWeekNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요");
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(",")) //리스트로 받아서 ,기준 나눠주고
                                        .map(String::trim)
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList());
        Validator.validateLastWeekNumbers(numbers);
        return numbers;
    }

    public static int getBonusBallNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
        int number = sc.nextInt();
        Validator.validateNumberRange(number);
        return number;
    }
}
