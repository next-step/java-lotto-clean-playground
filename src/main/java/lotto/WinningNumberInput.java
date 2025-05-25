package lotto;

import java.util.List;
import java.util.Scanner;

public class WinningNumberInput {
    private final Scanner scanner = new Scanner(System.in);
    private final WinningNumberParser winningNumberParser;

    public WinningNumberInput() {
        this(new WinningNumberParser());
    }

    public WinningNumberInput(WinningNumberParser winningNumberParser) {
        this.winningNumberParser = winningNumberParser;
    }

    public WinningNumber inputWinningNumber() {
        while (true) {
            String line = readLine();
            try {
                List<LottoNumber> list = winningNumberParser.parseNumbers(line);

                String bonusNumber = bonusReadLine();
                LottoNumber bonus = winningNumberParser.parseBonus(bonusNumber, list);
                return WinningNumber.of(list, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println("→ " + e.getMessage() + " 다시 입력해주세요.");
            }
        }
    }

    private static String readLine() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요 (예: 1,2,3,4,5,6): ");
        return new Scanner(System.in).nextLine();
    }

    private static String bonusReadLine() {
        System.out.println("보너스 번호를 입력해주세요: ");
        return new Scanner(System.in).nextLine();
    }
}
