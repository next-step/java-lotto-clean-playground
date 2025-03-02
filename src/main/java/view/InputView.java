package view;

import java.util.*;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.\n");
        return scanner.nextLine();
    }

    public int inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public List<String> inputManualCountList(int count) {
        List<String> manualNumbers = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        while (manualNumbers.size() < count) {
            String lottoNumbers = scanner.nextLine().trim();
            if (!lottoNumbers.isEmpty())
                manualNumbers.add(lottoNumbers);
        }
        return manualNumbers;
    }

    public String inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public int inputBonus() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
