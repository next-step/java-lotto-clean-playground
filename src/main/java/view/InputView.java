package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static int getPurchaseAmount() {
        System.out.println("구매 금액을 입력해주세요.");
        try {
            int amount = sc.nextInt();
            sc.nextLine(); // 개행 소비
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("숫자 외의 값이 입력됨");
            throw e;
        }
    }

    public static List<Integer> getLastWeekNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요");
        return Arrays.stream(sc.nextLine().split(",")) //리스트로 받아서 ,기준 나눠주고
                       .map(String::trim)
                       .map(Integer::parseInt)
                       .collect(Collectors.toList());
    }

    public static int getBonusBallNumber(){
        System.out.println("보너스 볼을 입력해주세요.");
        return sc.nextInt();
    }
}
