package util;

import java.util.List;
import java.util.Scanner;

public class InputFromUser {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputBuyingCosts() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static String inputLastWeekWinningLottoNumber() {
        return scanner.nextLine();
    }

    public static int inputLastWeekWinningLottoBonusNumber() {
        return scanner.nextInt();
    }

    public static int inputPassiveLottoCount() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> inputPassiveLottos() {
        String lottoNumbers = scanner.nextLine();
        return StringToIntegerConvertor.convertStringToInteger(LottoNumberSeparator.separateWinningLottoNumbers(lottoNumbers));
    }
}
