package view;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String readBudget() {
        System.out.println("구입금액을 입력해 주세요.");
        String budget = scanner.nextLine();
        System.out.println();
        return budget;
    }

    public int readManualLottoQuantity() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        int manualLottoQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        return manualLottoQuantity;
    }

    public List<List<LottoNumber>> readManualLottosNumber(int manualLottoQuantity) {
        List<List<LottoNumber>> manualLottosNumber = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해주세요");
        for (int i = 0; i < manualLottoQuantity; i++) {
            manualLottosNumber.add(readManualLottoNumber());
        }
        System.out.println();

        return manualLottosNumber;
    }

    private List<LottoNumber> readManualLottoNumber() {
        final String DELIMETER = ", ";
        List<LottoNumber> manualLottoNumber = new ArrayList<>();

        String manualInput = scanner.nextLine();

        try {
            String[] NumbersByString = manualInput.split(DELIMETER);

            for (String string : NumbersByString) {
                manualLottoNumber.add(new LottoNumber(Integer.valueOf(string)));
            }

            return manualLottoNumber;

        } catch (NullPointerException e) {
            throw new NullPointerException("입력 형식이 잘못되었습니다.");
        }
    }

    public List<LottoNumber> readWinLottoNumbers() {
        final String REGEX = ", ";
        List<LottoNumber> winNumbers = new ArrayList<>();

        System.out.println("지난 주 당첨번호를 입력해주세요");
        String str = scanner.nextLine();

        try {
            String[] winNumbersByString = str.split(REGEX);

            for (String string : winNumbersByString) {
                winNumbers.add(new LottoNumber(Integer.valueOf(string)));
            }

            return winNumbers;
        } catch (NullPointerException e) {
            throw new NullPointerException("입력 형식이 잘못되었습니다.");
        }
    }

    public String readBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        return scanner.nextLine();
    }
}
