package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String PAYMENT_ANOUNCEMENT = "구입금액을 입력해 주세요.";
    private final String WINNING_ANOUNCEMENT = "\n지난 주 당첨 번호를 입력해 주세요.";
    private final String BONUSBALL_ANOUNCEMENT = "\n보너스 볼을 입력해 주세요.";
    private final String MANUAL_COUNT_ANNOUNCEMENT = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private final String MANUAL_NUMBER_ANNOUNCEMENT = "\n수동으로 구매할 번호를 입력해 주세요.";
    private final Scanner SCANNER = new Scanner(System.in);

    public String readMoney(){
        System.out.println(PAYMENT_ANOUNCEMENT);
        return SCANNER.nextLine();
    }

    public String readManualTicketCount() {
        System.out.println(MANUAL_COUNT_ANNOUNCEMENT);
        return SCANNER.nextLine();
    }

    public List<Integer> readManualLottoNumber() {
        System.out.println(MANUAL_NUMBER_ANNOUNCEMENT);
        String manualLottoNumbers = SCANNER.nextLine();
        return Arrays.stream(manualLottoNumbers.split(",\\s*"))
                .map(Integer::parseInt)
                .toList();
    }

    public List<Integer> readWinningNumber() {
        System.out.println(WINNING_ANOUNCEMENT);
        String winningNumber = SCANNER.nextLine();
        return Arrays.stream(winningNumber.split(",\\s*"))
                .map(Integer::parseInt)
                .toList();
    }

    public int readBonusBall() {
        System.out.println(BONUSBALL_ANOUNCEMENT);
        return SCANNER.nextInt();
    }
}
