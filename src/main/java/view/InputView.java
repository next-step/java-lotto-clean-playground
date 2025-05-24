package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int inputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public List<Lotto> inputManualLottos(int count) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        scanner.nextLine(); // 버퍼 비우기

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            List<LottoNumber> numbers = parseNumbers(input);
            manualLottos.add(new Lotto(numbers));
        }
        return manualLottos;
    }

    public List<LottoNumber> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return parseNumbers(input);
    }

    public LottoNumber inputBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return new LottoNumber(bonusNumber);
    }

    private List<LottoNumber> parseNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
