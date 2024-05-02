package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public int readBudget() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
    }

    public List<Integer> readWinLottoNumbers(){
        final String REGEX = ", ";


        List<Integer> winNumbers = new ArrayList<>();

        System.out.println("지난 주 당첨번호를 입력해주세요");
        scanner.nextLine();
        String str = scanner.nextLine();

        String[] winNumbersByString = str.split(REGEX);

        for (String string : winNumbersByString) {
            int num = Integer.valueOf(string);
            winNumbers.add(num);
        }
        System.out.println(winNumbers);

        return winNumbers;
    }
}
