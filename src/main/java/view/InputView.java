package view;

import java.util.Arrays;

import domain.LottoNumber;
import domain.PurchasePrice;
import java.util.Scanner;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static PurchasePrice getPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        int purchasePrice = Integer.parseInt(input.trim());

        return new PurchasePrice(purchasePrice);
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


    public static LottoNumber getBonusBall() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();
        return new LottoNumber(bonusBall);
    }
}
