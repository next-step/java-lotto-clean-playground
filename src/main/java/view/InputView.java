package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static int getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    public static int getManualLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }


    public static List<String> getManualLottoNumber(int manualLottoCount) {
        List<String> manualLottoNumbers = new ArrayList<>();
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for(int i = 0; i < manualLottoCount; i++) {
            manualLottoNumbers.add(scanner.nextLine());
        }
        return manualLottoNumbers;
    }


    public static String[] getCorrectLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요. (쉼표로 구분)");
        String input = scanner.nextLine();
        String[] values = input.split(",");

        values = Arrays.stream(values).map(String::trim).toArray(String[]:: new); // 공백 처리

        long distinct = Arrays.stream(values).distinct().count(); // 중복 처리
        if(values.length != distinct){
            throw new IllegalArgumentException("로또는 중복될 수 없습니다.");
        }

        return values;
    }


    public static int getBonusBall() {
        System.out.println("\n보너스 볼을 입력해 주세요.");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }
}
