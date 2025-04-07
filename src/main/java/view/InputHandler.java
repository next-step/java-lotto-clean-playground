package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoNumber;
import model.LottoType;
import model.Money;
import model.WinningLotto;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            return new Money(Long.parseLong(input.trim()));
        } catch (Exception e) {
            System.out.println("입력값: " + input + " → 올바른 금액을 입력해주세요.");
            return inputMoney();
        }
    }

    public int inputManualCount(Money money) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            int count = Integer.parseInt(input.trim());
            int maxCount = money.divideByUnit();

            if (count > maxCount) {
                System.out.println("입력값: " + input + " → 구매 금액으로는 최대 " + maxCount + "장까지 구매할 수 있습니다.");
                return inputManualCount(money);
            }

            return count;
        } catch (Exception e) {
            System.out.println("입력값: " + input + " → 올바른 숫자를 입력해주세요.");
            return inputManualCount(money);
        }
    }

    public List<Lotto> inputManualLottos(int count) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = new ArrayList<>();

        while (lottos.size() < count) {
            lottos.add(readValidLotto());
        }

        return lottos;
    }

    private Lotto readValidLotto() {
        String line = scanner.nextLine();
        try {
            List<LottoNumber> numbers = Arrays.stream(line.split(","))
                    .map(s -> LottoNumber.valueOf(Integer.parseInt(s.trim())))
                    .collect(Collectors.toList());
            return new Lotto(numbers, LottoType.MANUAL);
        } catch (Exception e) {
            System.out.println("입력값: " + line + " → 올바른 형식으로 다시 입력해주세요.");
            return readValidLotto();
        }
    }

    public List<Integer> inputWinningLotto() {
        System.out.println("지난주 당첨 번호를 입력해 주세요.");
        String line = scanner.nextLine();

        try {
            return Arrays.stream(line.split(","))
                    .map(s -> Integer.parseInt(s.trim()))
                    .toList();
        } catch (Exception e) {
            System.out.println("입력값: " + line + " → 다시 올바르게 입력해주세요.");
            return inputWinningLotto();
        }
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (Exception e) {
            System.out.println("입력값: " + input + " → 다시 숫자를 입력해주세요.");
            return inputBonusNumber();
        }
    }
}
