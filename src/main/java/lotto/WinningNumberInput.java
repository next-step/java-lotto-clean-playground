package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WinningNumberInput {
    private final Scanner scanner = new Scanner(System.in);

    public WinningNumber inputWinningNumber() {
        while (true) {
            String line = readLine();
            try {
                List<LottoNumber> list = parseNumbers(line);

                String bonusNumber = bonusReadLine();
                LottoNumber bonus = parseBonus(bonusNumber, list);
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

    private static LottoNumber parseBonus(String bonusLine, List<LottoNumber> winningNumbers) {
        try {
            int bonusValue = Integer.parseInt(bonusLine.trim());
            LottoNumber bonus = new LottoNumber(bonusValue);
            if (winningNumbers.contains(bonus)) {
                throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
            return bonus;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
    }
    private static List<LottoNumber> parseNumbers(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("숫자는 반드시 6개여야 합니다.");
        }
        List<LottoNumber> list = new ArrayList<>();
        for (String lottoParts : parts) {
            try {
                list.add(new LottoNumber(Integer.parseInt(lottoParts.trim())));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
            }
        }
        return list;
    }
}
