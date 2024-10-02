package view;

import domain.Lotto;

import java.lang.reflect.Array;
import java.util.*;

public class InputView {

    public static final Scanner scanner = new Scanner(System.in);

    public static int readPrice(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static int numberOfhandLottos(){ // 수동 로또 개수 입력 메서드
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<Lotto> handLottosNumber(int numberOfHandLottos){ //수동 로또 번호 입력 메서드

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottoList = new ArrayList<>();

        for (int i=0; i< numberOfHandLottos; i++){
            List<String> splitNumbers = Arrays.asList(scanner.nextLine().split(",\\s*"));

            List<Integer> numbers = new ArrayList<>();
            for(String number: splitNumbers){
                numbers.add(Integer.parseInt(number));
            }

            lottoList.add(new Lotto(numbers));
        }
        return lottoList;
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

    public static int BonusBall (){
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
