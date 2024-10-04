package view;

import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner sc  = new Scanner(System.in);


    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");

        return   Integer.parseInt(sc.nextLine());
    }

    public int readManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");

        int count =  Integer.parseInt(sc.nextLine());

        if (count < 0) {
            throw  new IllegalArgumentException("수동 구매 로또 갯수는 0 이상이여야 합니다.");
        }

        return count;
    }

//    flag: 한번만 출력하기 위해서
    public LottoNumbers readManualLottoNumbers() {
        return numbersToLottoNumbers();
    }

    public void printReadManualLottoMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }
    public LottoNumbers readWinnerNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");

        return numbersToLottoNumbers();
    }

    public LottoNumber readBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        String number = sc.nextLine();

        return LottoNumber.from(number);
    }

    private LottoNumbers numbersToLottoNumbers() {
        String[] numbers = sc.nextLine().replaceAll(" ", "").split(",");

        List<LottoNumber> lottoNumbers = Arrays
                .stream(numbers)
                .map(LottoNumber::from)
                .collect(Collectors.toList());

        Collections.sort(lottoNumbers);

        return new LottoNumbers(lottoNumbers);
    }
}
