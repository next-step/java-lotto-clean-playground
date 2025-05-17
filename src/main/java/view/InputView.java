package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getLottoPurchasePrice() {
        int purchasePrice = scanner.nextInt();
        scanner.nextLine();
        return purchasePrice;
    }

    public Lotto getWinningLottoNumbers() {
        String input = scanner.nextLine();
        return new Lotto(new LottoNumbers(Arrays.stream(input.split(","))
                .map(s -> new LottoNumber(Integer.parseInt(s.trim())))
                .toList()));
    }

    public int getBonusNumber() {
        return scanner.nextInt();
    }

    public int getManualLottoCount() {
        int manualLottoCount = scanner.nextInt();
        scanner.nextLine();
        return manualLottoCount;
    }

    public List<List<Integer>> getManualLottos(int manualLottoCount) {
        List<List<Integer>> manualLottos = new ArrayList<>();

        for (int i = 0; i < manualLottoCount; i++) {
            String input = scanner.nextLine(); // 예: "8, 21, 23, 41, 42, 43"
            List<Integer> numbers = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
            manualLottos.add(numbers);
        }

        return manualLottos;
    }
}
