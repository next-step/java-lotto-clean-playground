package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner sc  = new Scanner(System.in);


    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        int amount =  Integer.parseInt(sc.nextLine());

        if (amount % (Lotto.PRICE) != 0) {
            throw new IllegalArgumentException("Lotto의 가격은 1000원입니다.");
        }

        return amount;
    }

    public int readManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(sc.nextLine());
    }

    public LottoNumbers readManualLottoNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        String[] numbers = sc.nextLine().replaceAll(" ", "").split(",");

        return new LottoNumbers(Arrays
                .stream(numbers)
                .map(LottoNumber::from)
                .collect(Collectors.toList()));
    }
}
