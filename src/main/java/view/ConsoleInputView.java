package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String readLottoPurchaseAmount() {
        return scanner.nextLine();
    }

    @Override
    public String readManualLottoCount() {
        return scanner.nextLine();
    }

    @Override
    public List<String> readManualLottoNumbers(int manualCount) {
        List<String> manualNumbers = new ArrayList<>(manualCount);
        for (int i = 0; i < manualCount; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }

    @Override
    public String readLastWeekWinningNumbers() {
        return scanner.nextLine();
    }

    @Override
    public String readBonusNumber() {
        return scanner.nextLine();
    }
}
