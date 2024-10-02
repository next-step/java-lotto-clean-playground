package view;

import domain.Lotto;
import domain.LottoFactory;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static int readPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static int numberOfhandLottos() { // 수동 로또 개수 입력 메서드
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<Lotto> handLottosNumber(int numberOfHandLottos) { //수동 로또 번호 입력 메서드

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        scanner.nextLine();

        List<List<Integer>> manualNumbers = new ArrayList<>();

        for (int i = 0; i < numberOfHandLottos; i++) {
            List<Integer> numbers = Arrays.stream(scanner.nextLine().split(",\\s*"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            manualNumbers.add(numbers);
        }
        return LottoFactory.createManualLottos(manualNumbers);
    }

    public static List<Integer> lottoWinningNumber() { //로또 당첨 번호 입력 메서드

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.stream(scanner.nextLine().split(",\\s*"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int bonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
