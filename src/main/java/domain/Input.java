package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public int setPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }

    public ArrayList<Integer> setWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();  // 전체 한 줄을 입력받음

        String[] numberStrings = line.split(", ");  // 공백을 기준으로 문자열 분리
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String numberString : numberStrings) {
            numbers.add(Integer.parseInt(numberString));
        }

        return numbers;
    }

    public int setBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();
    }
}
