package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    private static final String PRICE_QUERY = "구입금액을 입력해 주세요.";

    private static final String MANUAL_LOTTO_COUNT_QUERY = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS_QUERY = "수동으로 구매할 번호를 입력해 주세요.";

    private static final String WINNING_NUMBERS_QUERY = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_QUERY = "보너스 볼을 입력해 주세요.";

    public static int getPrice() {
        System.out.println(PRICE_QUERY);
        return readInt();
    }

    public static String getWinningNumbers() {
        System.out.println("\n" + WINNING_NUMBERS_QUERY);
        return readString();
    }

    public static int getBonusNumber() {
        System.out.println("\n" + BONUS_NUMBER_QUERY);
        return readInt();
    }

    public static int getManualLottoCount() {
        System.out.println("\n" + MANUAL_LOTTO_COUNT_QUERY);
        return readInt();
    }

    private static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static List<String> getManualLottoNumbers(final int manualLottoCount) {
        System.out.println("\n" + MANUAL_LOTTO_NUMBERS_QUERY);
        // manualLottoCount만큼 readString 받은 다음 List<String> 반환
        return readStringCountTimes(manualLottoCount);
    }

    private static List<String> readStringCountTimes(int count) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(readString());
        }
        return values;
    }

    private static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
