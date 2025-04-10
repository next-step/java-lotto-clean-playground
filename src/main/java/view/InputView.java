package view;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Integer inputPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static Integer inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<String> inputManualLottoNumbers(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> numbers = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < manualLottoCount; i++) {
            numbers.add(scanner.nextLine());
        }
        return numbers;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> winningNumbers = Arrays.asList(scanner.nextLine().split(","));

        return winningNumbers.stream()
                .map(number -> Integer.parseInt(number.trim()))
                .collect(Collectors.toList());
    }

    public static Integer inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

}
