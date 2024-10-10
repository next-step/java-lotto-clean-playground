package util;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    public static List<String> inputPassiveLottos(final int passiveLottoCount) {
        return IntStream.range(0, passiveLottoCount)
                .mapToObj(i -> scanner.nextLine())
                .toList();
    }
}
