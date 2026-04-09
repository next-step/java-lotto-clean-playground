package view;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        int amount = scanner.nextInt();

        if (amount < 1000) {
            throw new IllegalArgumentException("구입 금액은 천원 이상의 양수값을 입력해주세요!");
        }
        scanner.nextLine();

        return amount;
    }

    public int getManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = scanner.nextInt();

        if (manualCount < 0) {
            throw new IllegalArgumentException("0 이상의 수를 입력해주세요!");
        }

        scanner.nextLine();

        return manualCount;
    }

    public List<Lotto> getManuallyPurchasedLottos(int manualPurchaseAmount) {
        System.out.println("수동으로 구매할 로또 번호를 입력해주세요.");

        List<Lotto> manuallyPurchasedLottos = new ArrayList<>();

        for (int i = 0; i < manualPurchaseAmount; i++) {
            String numbers = scanner.nextLine();
            String[] numbersArray = numbers.split(",");

            List<LottoNumber> lottoNumbers = new ArrayList<>();
            for (String number : numbersArray) {
                int parsedNumber = Integer.parseInt(number.trim());
                lottoNumbers.add(new LottoNumber(parsedNumber));
            }
            manuallyPurchasedLottos.add(new Lotto(lottoNumbers));
        }

        return manuallyPurchasedLottos;
    }

    public List<LottoNumber> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");

        List<LottoNumber> winningNumbers = new ArrayList<>();

        String numbers = scanner.nextLine();
        String[] numbersArr = numbers.split(",");

        for (String number : numbersArr) {
            int parsedNumber = Integer.parseInt(number.trim());
            winningNumbers.add(new LottoNumber(parsedNumber));
        }

        return winningNumbers;
    }

    public LottoNumber getBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");

        int bonusNumber = scanner.nextInt();
        scanner.nextLine();

        return new LottoNumber(bonusNumber);
    }
}
