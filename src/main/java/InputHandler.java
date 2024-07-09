import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualCount = scanner.nextInt();
        scanner.nextLine();
        return manualCount;
    }

    public List<Lotto> getManualLottos(int manualCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            String input = scanner.nextLine();
            String[] inputs = input.split(", ");
            List<Integer> numbers = new ArrayList<>();
            for (String number : inputs) {
                numbers.add(Integer.parseInt(number));
            }
            manualLottos.add(new Lotto(numbers));
        }
        return manualLottos;
    }

    public WinningNumbers getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
        String input = scanner.nextLine();
        String[] inputs = input.split(", ");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String number : inputs) {
            winningNumbers.add(Integer.parseInt(number));
        }
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return new WinningNumbers(winningNumbers, bonusNumber);
    }
}
