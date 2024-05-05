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
            int budget = scanner.nextInt();
            scanner.nextLine();
            return budget;
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }
    }

    public int readManualQuantity(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        int manualNum = scanner.nextInt();
        scanner.nextLine();

        return manualNum;
    }

    public List<List<Integer>> readManualLottoNumbers(int manualNum) {

        List<List<Integer>> manualNumbers = new ArrayList<>();

        // 수동 로또 번호를 받아서 로또 객체를 만들어야 하는데 Lotto 객체 생성자가 이미 랜덤으로 번호를 만들 수 있게끔 설정을 해버렸음.
        // 여기부터 수정 필요.

        System.out.println("수동으로 구매할 번호를 입력해주세요");
        for (int i = 0; i < manualNum; i++) {
            manualNumbers.add(readManulNumber());
        }

        return manualNumbers;
    }

    private List<Integer> readManulNumber(){
        final String REGEX = ", ";
        List<Integer> manualNumber = new ArrayList<>();

        String str = scanner.nextLine();

        String[] winNumbersByString = str.split(REGEX);

        for (String string : winNumbersByString) {
            manualNumber.add(Integer.valueOf(string));
        }

        return manualNumber;
    }

    public List<Integer> readWinLottoNumbers() {
        final String REGEX = ", ";
        List<Integer> winNumbers = new ArrayList<>();

        System.out.println("지난 주 당첨번호를 입력해주세요");
        String str = scanner.nextLine();

        String[] winNumbersByString = str.split(REGEX);

        for (String string : winNumbersByString) {
            winNumbers.add(Integer.valueOf(string));
        }

        return winNumbers;
    }

    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        return scanner.nextInt();
    }
}
