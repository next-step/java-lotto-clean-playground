package view;

import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = scanner.nextInt();
        scanner.nextLine();

        return money;
    }

    public int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        scanner.nextLine();

        return count;
    }

    public List<List<Integer>> getManualTicketNumbers(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualNumbers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            manualNumbers.add(parseNumbers(scanner.nextLine()));
        }

        return manualNumbers;
    }

    public List<LottoNumber> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();

        return parseNumbers(winningNumbers).stream()
                .map(LottoNumber::new)
                .toList();
    }

    public LottoNumber getBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = scanner.nextInt();
        scanner.nextLine();

        return new LottoNumber(bonus);
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}
