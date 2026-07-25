package view;

import lotto.Lotto;
import lotto.LottoNumber;
import lotto.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseAmount readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return new PurchaseAmount(Integer.parseInt(SCANNER.nextLine()));
    }

    public static Lotto readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return parseWinningLotto(SCANNER.nextLine());
    }

    static Lotto parseWinningLotto(String input) {
        List<LottoNumber> numbers = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        while (tokenizer.hasMoreTokens()) {
            numbers.add(toLottoNumber(tokenizer.nextToken()));
        }
        return new Lotto(numbers);
    }

    private static LottoNumber toLottoNumber(String value) {
        return new LottoNumber(Integer.parseInt(value.trim()));
    }

}
