package view;

import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.Money;

import java.io.InputStream;
import java.util.*;

public class InputView {

    private final Scanner scanner;

    public InputView(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Lotto lastWeekWinningNumbers() {
        Lotto lotto;

        do {
            lotto = validNumbers();
        } while (lotto == null);

        return lotto;
    }

    private Lotto validNumbers() {
        try {
            return new Lotto(initNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println("당첨 번호를 입력할 때에는 6개의 숫자여야 하며, 콤마로 구분되어 있어야 합니다.");
        }
        return null;
    }

    private List<LottoNumber> initNumbers() {
        String input = scanner.nextLine();

        if (!input.contains(",")) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
    }

    public Money payment() {

        Integer payment;

        do {
            payment = validationMoney();
        } while (payment == null);

        return new Money(payment);
    }

    private Integer validationMoney() {
        try {
            return initPayment();
        } catch (InputMismatchException e) {
            System.out.println("양의 정수를 입력해주세요.");
            scanner.nextLine();
        }
        return null;
    }

    private int initPayment() {
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (amount <= 0) {
            throw new InputMismatchException();
        }

        return amount;
    }

    public LottoNumber bonusNumber() {
        return initBonusNumber();
    }

    private LottoNumber initBonusNumber() {
        LottoNumber bonusNumber = new LottoNumber(scanner.nextInt());
        scanner.nextLine();

        return bonusNumber;
    }
}
