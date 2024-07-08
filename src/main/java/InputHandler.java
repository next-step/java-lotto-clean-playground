import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public WinningNumbers getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        scanner.nextLine();
        String input = scanner.nextLine();
        String[] inputs = input.split(", ");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : inputs) {
            winningNumbers.add(Integer.parseInt(number));
        }
        return new WinningNumbers(winningNumbers);
    }
}
