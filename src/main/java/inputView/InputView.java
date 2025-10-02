package inputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String inputPrice() {
        return scanner.nextLine();
    }

    public String inputManualCount() {
        return scanner.nextLine();
    }

    public List<String> inputManualLottos(int count) {
        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            inputs.add(scanner.nextLine());
        }
        return inputs;
    }

    public String inputBonusBall() {
        return scanner.nextLine();
    }

    public String inputWinningNumbers() {
        return scanner.nextLine();
    }
}
