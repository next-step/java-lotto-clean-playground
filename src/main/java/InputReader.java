import java.util.*;
import java.util.stream.Collectors;

public class InputReader {
    private final Scanner sc;

    public InputReader(Scanner sc) {
        this.sc = sc;
    }

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = sc.nextInt();
        sc.nextLine();
        return amount;
    }

    public List<LottoNumber> readWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();
        return Arrays.stream(input.split(","))
                .map(s -> new LottoNumber(Integer.parseInt(s.trim())))
                .collect(Collectors.toList());
    }
}

