package view;

import domain.LottoNumber;
import domain.Row;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int getPrice() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int price = scanner.nextInt();
            scanner.nextLine();
            minCheck(price);
            System.out.println("");
            return price;
        } catch (Exception e) {
            throw new IllegalArgumentException("금액은 정수로 입력해주세요.");
        }
    }

    public Row getAnswer() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            Row row = new Row(Arrays
                    .stream(scanner.nextLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(num -> new LottoNumber(num))
                    .toList());
            System.out.println("");
            return row;
        } catch (Exception e) {
            throw new IllegalArgumentException("당첨번호는 , 로 구분된 숫자입니다.");
        }
    }

    public LottoNumber getBonus() {
        try {
            System.out.println("보너스 볼을 읿력해 주세요.");
            LottoNumber bonusNumBer = new LottoNumber(scanner.nextInt());
            scanner.nextLine();
            System.out.println("");
            return bonusNumBer;
        } catch (Exception e) {
            throw new IllegalArgumentException("보너스 번호는 숫자입니다.");
        }
    }

    public void minCheck(int price) {
        if (price < 1000) {
            throw new IllegalArgumentException("최소 금액은 1000원 입니다.");
        }
    }

    public int getManualNumber() {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            int manualNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.println("");
            return manualNumber;
        } catch (Exception e) {
            throw new IllegalArgumentException("수동 로또 수는 숫자입니다.");
        }
    }

    public List<Row> getManualRows(int manualNumber) {
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            List<Row> manualRows = new ArrayList<>();
            for (int i = 0; i < manualNumber; i++) {
                Row row = getManualRow();
                manualRows.add(row);
            }
            System.out.println("");
            return manualRows;
        } catch (Exception e) {
            throw new IllegalArgumentException("당첨번호는 , 로 구분된 숫자입니다.");
        }
    }

    public Row getManualRow() {
        return new Row(Arrays
                .stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .mapToObj(num -> new LottoNumber(num))
                .toList());
    }
}