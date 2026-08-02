package view;

import java.util.ArrayList;
import java.util.Scanner;

public final class InputView {
    public static Scanner lottoScanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputLottoTotalPrice(){
        System.out.println("구입 금액을 입력해 주세요.");
        try {
            return Integer.parseInt(lottoScanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자로만 입력해야 합니다.");
        }
    }

    public static int inputUserSelectedLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return Integer.parseInt(lottoScanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 개수는 숫자로만 입력해야 합니다.");
        }
    }

    public static ArrayList<String> inputUserSelectedLottoNumbers(int userSelectedNumbersCount) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        ArrayList<String> userSelectedNumbers = new ArrayList<>();
        for (int i = 0; i < userSelectedNumbersCount; i++) {
            userSelectedNumbers.add(lottoScanner.nextLine());
        }
        return userSelectedNumbers;
    }

    public static String inputWinningLottoNumbers(){

        System.out.println("\n지난 주 당첨번호를 입력해 주세요");
        String winningLottoNumbers = lottoScanner.nextLine();

        return winningLottoNumbers;
    }

    public static String inputBonusBallNumber(){
        System.out.println("\n보너스 볼을 입력해 주세요.");
        String bonusNumber = lottoScanner.nextLine();
        if (bonusNumber.contains(" ") || bonusNumber.contains(",")) {
            throw new IllegalArgumentException("보너스 볼은 하나의 숫자만 입력해야 합니다.");
        }
        return bonusNumber;
    }

    public static void closeScanner(Scanner scanner) {
        if (scanner != null) {
            scanner.close();
        }
    }
}
