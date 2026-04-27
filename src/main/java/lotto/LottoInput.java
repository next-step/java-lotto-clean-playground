package lotto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LottoInput {
    private final Scanner scanner = new Scanner(System.in);
    private final LottoParser lottoParse = new LottoParser();


    public LottoPrice inputPrice() {
        System.out.println("구입금액을 입력해 주세요. (ex. 1000)");
        try {
            return new LottoPrice(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public int inputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요. (구매 로또 수 이하여야 합니다.)");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public List<Lotto> getManualLottos(int manualCount) {
        while (true) {
            try {
                List<String> rawNumbers = inputManualNumbers(manualCount);
                return rawNumbers.stream()
                        .map(lottoParse::parse)
                        .toList();
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        }
    }

    public String inputWinningNumbers() {
        System.out.println("\n지난주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    private List<String> inputManualNumbers(int count) {
        if (count <= 0) {
            return List.of();
        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, count)
                .mapToObj(i -> scanner.nextLine())
                .toList();
    }
}
