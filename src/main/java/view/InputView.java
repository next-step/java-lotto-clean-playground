package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = scanner.nextInt();
        scanner.nextLine();

        return purchaseAmount;
    }

    public static int getManualTicketCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<List<Integer>> getManualNumbers(int count) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        scanner.nextLine(); // 개행 문자 처리
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            List<Integer> numbers = parseNumbers(input);
            manualNumbers.add(numbers);
        }
        return manualNumbers;
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    private static List<Integer> parseNumbers(String input) {
        return List.of(input.split(",")).stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int getBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");

        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
