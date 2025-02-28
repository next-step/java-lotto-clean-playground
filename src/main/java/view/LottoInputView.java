package view;

import domain.*;
import java.util.*;

public class LottoInputView implements LottoView {

    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = readInt();
        scanner.nextLine();
        return amount;
    }

    public int getManualLottoCount(LottoCount totalLottoCount) {
        printEmptyLine();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = readInt();
        validateManualLottoCount(count, totalLottoCount.getCount());
        scanner.nextLine();
        return count;
    }

    public List<Lotto> getManualLottos(LottoCount manualLottoCount) {
        printEmptyLine();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();

        for (int i = 0; i < manualLottoCount.getCount(); i++) {
            manualLottos.add(new Lotto(convertToLottoNumbers(readLottoNumbers())));
        }

        return manualLottos;
    }

    public WinningLottoNumbers inputWinningLottoNumbers() {
        return new WinningLottoNumbers(convertToLottoNumbers(inputWinningNumbers()), inputBonusBall());
    }

    private List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return readLottoNumbers();
    }

    private BonusBall inputBonusBall() {
        printEmptyLine();
        System.out.println("보너스 볼을 입력해 주세요.");
        return BonusBall.of(readInt());
    }

    private List<Integer> readLottoNumbers() {
        String input = scanner.nextLine();
        return parseWinningNumbers(input);
    }

    private List<Integer> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .toList();
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("숫자를 입력해 주세요.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void validateManualLottoCount(int manualCount, int totalCount) {
        if (manualCount < 0 || manualCount > totalCount) {
            throw new IllegalArgumentException("수동 로또 개수는 0~" + totalCount + " 사이여야 합니다.");
        }
    }
}
