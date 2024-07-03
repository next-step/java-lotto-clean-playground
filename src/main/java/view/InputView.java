package view;

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

    public static LottoResult inputWinningNumbers() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> list = Arrays.stream(scanner.nextLine().split(", "))
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        return new LottoResult(new LottoTicket(list));
    }
}
