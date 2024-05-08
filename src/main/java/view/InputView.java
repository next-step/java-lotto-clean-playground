package view;

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

    public List<List<Integer>> readManualLottosNumber(int manualLottoQuantity) {
        List<List<Integer>> manualLottosNumber = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해주세요");
        for (int i = 0; i < manualLottoQuantity; i++) {
            manualLottosNumber.add(readManualLottoNumber());
        }
        System.out.println();

        return manualLottosNumber;
    }

    private List<Integer> readManualLottoNumber() {
        final String DELIMETER = ", ";
        List<Integer> manualLottoNumber = new ArrayList<>();

        String manualInput = scanner.nextLine();

        String[] NumbersByString = manualInput.split(DELIMETER);

        for (String string : NumbersByString) {
            manualLottoNumber.add(Integer.valueOf(string));
        }

        return manualLottoNumber;
    }

    public List<Integer> readWinLottoNumbers() {
        final String REGEX = ", ";
        List<Integer> winNumbers = new ArrayList<>();

        System.out.println("지난 주 당첨번호를 입력해주세요");
        String str = scanner.nextLine();

        String[] winNumbersByString = str.split(REGEX);

        for (String string : winNumbersByString) {
            winNumbers.add(Integer.valueOf(string));
        }

        return winNumbers;
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        return scanner.nextInt();
    }
}
