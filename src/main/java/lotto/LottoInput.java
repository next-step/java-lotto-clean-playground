package lotto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class LottoInput {
    private final Scanner scanner = new Scanner(System.in);

    public int inputPrice() {
        System.out.println("구입금액을 입력해 주세요. (ex. 1000)");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
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

    public List<String> inputManualNumbers(int count) {
        if (count <= 0) {
            return List.of();
        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, count)
                .mapToObj(i -> scanner.nextLine())
                .toList();
    }
}
