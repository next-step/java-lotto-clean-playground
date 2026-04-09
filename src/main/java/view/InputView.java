package view;

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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}