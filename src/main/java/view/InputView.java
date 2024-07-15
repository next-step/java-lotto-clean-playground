package view;

import domain.BonusNum;
import dto.RowDto;
import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPrice() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int price = Integer.parseInt(scanner.nextLine());
            minCheck(price);
            return price;
        } catch (Exception e) {
            throw new IllegalArgumentException("금액은 정수로 입력해주세요.");
        }
    }

    public RowDto getAnswer() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            RowDto row = new RowDto(Arrays
                    .stream(scanner.nextLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList());
            System.out.println("");
            return row;
        } catch (Exception e) {
            throw new IllegalArgumentException("당첨번호는 , 로 구분된 숫자입니다.");
        }
    }

    public BonusNum getBonus() {
        try {
            System.out.println("보너스 볼을 읿력해 주세요.");
            BonusNum bonusNum = new BonusNum(Integer.parseInt(scanner.nextLine()));
            System.out.println("");
            return bonusNum;
        } catch (Exception e) {
            throw new IllegalArgumentException("보너스 번호는 숫자입니다.");
        }
    }

    public void minCheck(int price) {
        if (price < 1000) {
            throw new IllegalArgumentException("최소 금액은 1000원 입니다.");
        }
    }
}
