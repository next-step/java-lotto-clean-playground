package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public int readLottoPurchaseAmount() {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public int readManualLottoCount() {
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public List<String> readManualLottoNumbers(int count) {
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualNumbers.add(scanner.nextLine().trim());
        }
        return manualNumbers;
    }

    public String readWinningNumbers() {
        return scanner.nextLine().trim();
    }

    public int readBonusNumber() {
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
