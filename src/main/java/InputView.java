import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    public int inputLottoPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextInt();
    }

    public Lotto inputLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return stringToLotto(SCANNER.nextLine());
    }

    public Lotto stringToLotto(String string) {
        List<Integer> numbers = Arrays.stream(string.split(",")).map(Integer::parseInt).toList();
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }
}
