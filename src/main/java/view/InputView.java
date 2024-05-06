package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final static String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private final static String MANUAL_BUY_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private final static String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private final static String INPUT_WINNER_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";


    private static final Scanner scanner = new Scanner(System.in);


    public String inputPrice(){
        System.out.println(INPUT_PRICE);
        return scanner.nextLine();
    }

    public String inputManualLottoCount(){
        System.out.println();
        System.out.println(MANUAL_BUY_LOTTO);
        return scanner.nextLine();
    }

    public List<String> manualLottos(int cnt){
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS);
        List<String> manualLottoNums = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            manualLottoNums.add(scanner.nextLine());
        }
        return manualLottoNums;
    }

    public String inputWinnerNumber(){
        System.out.println();
        System.out.println(INPUT_WINNER_NUMBER);
        return scanner.nextLine();
    }

    public String inputBonusNumber(){

        System.out.println();
        System.out.println(INPUT_BONUS_NUMBER);
        return scanner.nextLine();
    }
}
