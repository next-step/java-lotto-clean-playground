package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Lotto> getManualLottos(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return IntStream.range(0, count)
                .mapToObj(i -> getLottoNumbers())
                .collect(Collectors.toList());
    }

    public static Lotto getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoNumbers();
    }

    private static Lotto getLottoNumbers() {
        String input = scanner.nextLine();
        List<LottoNumber> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public static LottoNumber getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int number = Integer.parseInt(scanner.nextLine());
        return new LottoNumber(number);
    }
}
