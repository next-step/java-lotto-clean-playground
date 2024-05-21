package domain;

import exception.BonusNumber;
import exception.Lotto;
import exception.Money;

import java.util.ArrayList;
import java.util.Scanner;

public record Input() {

    public int setPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);

        return new Money(scanner.nextInt()).money();
    }

    public ArrayList<Integer> setWinningNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        return new Lotto(separateNumber(line)).lotto();
    }

    public int setBonusNumber(ArrayList<Integer> winningNumbers) {
        System.out.println("\n보너스 볼을 입력해 주세요");
        Scanner scanner = new Scanner(System.in);
        int bonusNumber = scanner.nextInt();

        winningNumbers.add(bonusNumber);

        return new BonusNumber(winningNumbers, bonusNumber).bonusNumber();
    }

    public int setManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextInt();

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


}
