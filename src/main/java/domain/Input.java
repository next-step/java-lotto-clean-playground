package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public int setPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);

        int money = scanner.nextInt();

        if (validateNegativeNumber(money)) {
            throw new IllegalArgumentException();
        }

        return money;
    }

    public ArrayList<Integer> setWinningNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        int count = separateNumber(line).size();

        if (
                validateNegativeNumber(count)
                        ||
                        validateLottoLength(count)
                        ||
                        separateNumber(line).stream().distinct().count() != 6
        ) {
            throw new IllegalArgumentException();
        }

        return separateNumber(line);
    }

    public int setBonusNumber(ArrayList<Integer> winningNumbers) {
        System.out.println("\n보너스 볼을 입력해 주세요");
        Scanner scanner = new Scanner(System.in);
        int bonusNumber = scanner.nextInt();

        winningNumbers.add(bonusNumber);

        if (winningNumbers.stream().distinct().count() != 7) {
            throw new IllegalArgumentException("보너스 숫자는 중복이 불가능합니다.");
        }
        return bonusNumber;
    }

    public int setManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        if (validateNegativeNumber(count)) {
            throw new IllegalArgumentException();
        }
        return count;

    }

    public ArrayList<Integer> setManualNumber() {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        return separateNumber(line);
    }

    public ArrayList<Integer> separateNumber(String raw) {
        String[] numberStrings = raw.split(", ");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String numberString : numberStrings) {
            numbers.add(Integer.parseInt(numberString));
        }

        return numbers;
    }

    public boolean validateNegativeNumber(int number) {
        return number < 0;
    }

    public boolean validateLottoLength(int number) {
        return number > 6;
    }

}
