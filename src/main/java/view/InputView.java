package view;

import domain.lotto.LottoNumber;
import domain.common.Money;

import domain.lotto.LottoTicket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ", ";

    public static Money inputMoney() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextInt());
    }

    public static LottoTicket inputWinningNumbers() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<LottoNumber> list = Arrays.stream(scanner.nextLine().split(DELIMITER))
            .mapToInt(Integer::parseInt)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        return new LottoTicket(list);
    }

    public static LottoNumber inputBonusNumber() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(scanner.nextInt());
    }

    public static int inputManualCount() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<String[]> inputManualLotto(final int count) {
        List<String[]> inputs = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < count; i++) {
            inputs.add(sc.nextLine().split(DELIMITER));
        }
        return inputs;
    }
}
