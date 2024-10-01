package view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static int readPrice(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<String> readLottoNumbers(){ //로또 구입 금액 입력 메서드
        System.out.println("개를 구매했습니다.");

        final String lottos = scanner.nextLine();
        return new ArrayList<>(Arrays.asList(lottos.split("")));
    }

    public static List<Integer> LottoWinningNumber (int numberOfLottos) { //로또 당첨 번호 입력 메서드

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> splitNumbers = Arrays.asList(scanner.nextLine().split(",\\s*"));

        List<Integer> winningNumbers = new ArrayList<>();
        for (String number: splitNumbers){
            winningNumbers.add(Integer.parseInt(number));
        }

        return winningNumbers;
    }
}
