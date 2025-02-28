package view;

import domain.WinningLottoNumbers;
import java.util.*;

public class LottoInputView implements LottoView {

    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = readInt();
        scanner.nextLine();
        return amount;
    }

    public WinningLottoNumbers getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new WinningLottoNumbers(readWinningNumbers());
    }

    private List<Integer> readWinningNumbers() {
        String input = scanner.nextLine();
        return parseWinningNumbers(input);
    }

    private List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("숫자를 입력해 주세요.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
