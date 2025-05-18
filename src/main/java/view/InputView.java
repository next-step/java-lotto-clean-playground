package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String readLottoPurchaseAmount() {
        return scanner.nextLine();
    }

    public String readManualLottoCount() {
        return scanner.nextLine();
    }

    public List<String> readManualLottoNumbers(int manualCount) {
        List<String> manualNumbers = new ArrayList<>(manualCount);
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }

    public String readLastWeekWinningNumbers() {
        return scanner.nextLine();
    }

    public String readBonusNumber() {
        return scanner.nextLine();
    }
}
