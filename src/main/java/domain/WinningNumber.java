package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinningNumber {
    private static Scanner scanner = new Scanner(System.in);

    public List<Integer> getWinningNumber() {
        List<Integer> winningNumber = new ArrayList<>();
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        String enteredWinningNumber = scanner.nextLine();
        String[] item = enteredWinningNumber.split(",");
        for (int i = 0; i < item.length; i++) {
            item[i] = item[i].trim();
            winningNumber.add(Integer.parseInt(item[i]));
        }

        return winningNumber;
    }
}
