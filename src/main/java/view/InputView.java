package view;

import domain.lotto.Lotto;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.money.Money;
import domain.lotto.wrap.money.Payment;

import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

    public Lotto manualLottoNumbers() {
        Lotto lotto;

        do {
            lotto = validNumbers();
        } while (lotto == null);

        return lotto;
    }

    public Payment payment() {
        Integer payment;
        do {
            payment = validationMoney();
        } while (payment == null);

        return new Money(payment);
    }

    public int manualCount() {
        Integer count;

        do {
            count = validationManualCount();
        } while (count == null);

        return count;
    }

    public LottoNumber bonusNumber() {
        return initBonusNumber();
    }

    private Lotto validNumbers() {
        try {
            return new Lotto(initNumbers());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<LottoNumber> initNumbers() {
        String input = scanner.nextLine();

        if (!input.contains(",")) {
            throw new IllegalArgumentException("당첨 번호를 입력할 때에는 6개의 숫자여야 하며, 콤마로 구분되어 있어야 합니다.");
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();
    }

    private Integer validationMoney() {
        try {
            return initPayment();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return null;
    }

    private int initPayment() {
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (amount <= 0) {
            throw new InputMismatchException("양의 정수를 입력해주세요.");
        }

        return amount;
    }


    private Integer validationManualCount() {
        try {
            return initManualCount();
        } catch (InputMismatchException e) {
            System.out.println("0 이상의 정수를 입력해주세요.");
            scanner.nextLine();
        }
        return null;
    }

    private int initManualCount() {
        int count = scanner.nextInt();
        scanner.nextLine();

        if (count < 0) {
            throw new InputMismatchException();
        }

        return count;
    }

    private LottoNumber initBonusNumber() {
        LottoNumber bonusNumber = new LottoNumber(scanner.nextInt());
        scanner.nextLine();

        return bonusNumber;
    }
}
