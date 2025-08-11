package view;

import model.ManualCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner sc = new Scanner(System.in);

    public String inputPurchaseAmount() {
        System.out.println("구입 금액을 입력해주세요.");

        return sc.nextLine();
    }

    public String inputWinningNumbers() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return sc.nextLine();
    }

    public String inputBonusNumber() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");

        return sc.nextLine();
    }

    public String inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return sc.nextLine();
    }

    public List<String> inputManualLottoNumbers(ManualCount count) {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<String> manualNumbers = new ArrayList<>();

        for (int i = 0; i < count.getManualCount(); i++) {
            String numbers = sc.nextLine();
            manualNumbers.add(numbers);
        }

        return manualNumbers;
    }
}
