import java.util.*;
import java.util.stream.Collectors;

public class InputReader {
    private final Scanner sc;

    public InputReader(Scanner sc) {
        this.sc = sc;
    }

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = sc.nextInt();
        sc.nextLine();
        return amount;
    }
    public int readManualLottoCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int count = sc.nextInt();
        sc.nextLine();
        return count;
    }

    public List<Lotto> readManualLottos(int count) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String input = sc.nextLine();
            List<LottoNumber> numbers = Arrays.stream(input.split(","))
                    .map(s -> new LottoNumber(Integer.parseInt(s.trim())))
                    .collect(Collectors.toList());
            manualLottos.add(new Lotto(numbers));
        }

        return manualLottos;
    }

    public List<LottoNumber> readWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String input = sc.nextLine();
        return Arrays.stream(input.split(","))
                .map(s -> new LottoNumber(Integer.parseInt(s.trim())))
                .collect(Collectors.toList());
    }

    public LottoNumber readBonusBall() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusBall = sc.nextInt();
        sc.nextLine();
        return new LottoNumber(bonusBall);
    }
}
