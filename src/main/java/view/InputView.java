package view;

import domain.lotto.LottoNumber;
import domain.lotto.LottoResult;
import domain.common.Money;

import domain.lotto.LottoTicket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    public static Money inputMoney() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextInt());
    }

    public static LottoTicket inputWinningNumbers() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<LottoNumber> list = Arrays.stream(scanner.nextLine().split(", "))
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
}
