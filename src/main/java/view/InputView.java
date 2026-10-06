package view;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static int readPrice(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine().trim();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다. 입력값: " + input);
        }
    }

    public static List<Integer> readWinningNumbers(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return readNumbers(scanner);
    }

    private static List<Integer> readNumbers(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(",", -1))
                .map(String::trim)
                .map(InputView::parseNumber)
                .toList();
    }

    private static int parseNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 숫자여야 합니다. 잘못된 입력값: " + value);
        }
    }

    public static LottoNumber readBonusNumber(Scanner scanner) {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(parseNumber(scanner.nextLine().trim()));
    }

    public static int readManualCount(Scanner scanner) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return parseNumber(scanner.nextLine().trim());
    }

    public static List<Lotto> readManualLottos(Scanner scanner, int manualCount) {
        List<Lotto> lottos = new ArrayList<>();
        printManualPrompt(manualCount);
        for (int count = 0; count < manualCount; count++) {
            lottos.add(new Lotto(readNumbers(scanner)));
        }
        return lottos;
    }

    private static void printManualPrompt(int manualCount) {
        if (manualCount > 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }
    }
}
