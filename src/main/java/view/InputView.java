package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.PurchaseAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                System.out.println("구입 금액을 입력해 주세요.");
                int amount = Integer.parseInt(scanner.nextLine().trim());
                return new PurchaseAmount(amount);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int getManualCount() {
        while (true) {
            try {
                System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            }
        }
    }

    public List<Lotto> getManualLottos(int manualCount) {
        if (manualCount == 0) {
            return new ArrayList<>();
        }

        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(getManualLotto());
        }
        return manualLottos;
    }

    private Lotto getManualLotto() {
        while (true) {
            try {
                String[] numbersArr = scanner.nextLine().split(",");
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for (String number : numbersArr) {
                    lottoNumbers.add(new LottoNumber(Integer.parseInt(number.trim())));
                }
                return new Lotto(lottoNumbers);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<LottoNumber> getWinningNumbers() {
        while (true) {
            try {
                System.out.println("지난 주 당첨 번호를 입력해주세요.");
                String[] numbersArr = scanner.nextLine().split(",");

                List<LottoNumber> winningNumbers = new ArrayList<>();
                for (String number : numbersArr) {
                    winningNumbers.add(new LottoNumber(Integer.parseInt(number.trim())));
                }
                return winningNumbers;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public LottoNumber getBonusNumber() {
        while (true) {
            try {
                System.out.println("보너스 볼을 입력해주세요.");
                int bonusNumber = Integer.parseInt(scanner.nextLine().trim());
                return new LottoNumber(bonusNumber);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}