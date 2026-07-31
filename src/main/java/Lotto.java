import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Lotto {

    private final int LOTTO_THRESHOLD = 45;
    private final int NUMBER_THRESHOLD = 6;

    Scanner scanner = new Scanner(System.in);

    int inputPurchasePrice() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int purchasePrice = scanner.nextInt();

            if (purchasePrice % 1000 != 0) {
                throw new IllegalArgumentException("1000의 배수로 입력해주세요.");
            }

            if (purchasePrice <= 0) {
                throw new IllegalArgumentException("양수의 가격을 입력해주세요.");
            }

            System.out.println();

            return purchasePrice;
        }
        catch (InputMismatchException e){
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }


    int getNumber(int purchasePrice){
        return purchasePrice/1000;
    }


    List<Integer> createNumbers(){
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= LOTTO_THRESHOLD; i++){
            numbers.add(i);
        }
        return numbers;
    }


    List<Integer> shuffleNumbers(){
        List<Integer> numbers = createNumbers();
        Collections.shuffle(numbers);
        return numbers;
    }


    List<Integer> sortLotto(List<Integer> lotto){
        Collections.sort(lotto);
        return lotto;
    }


    List<Integer> getLottoNumbers(List<Integer> numbers){
        List<Integer> lotto = new ArrayList<>();

        for(int i = 0; i < NUMBER_THRESHOLD; i++){
            lotto.add(numbers.get(i));
        }
        return lotto;
    }


    List<Integer> oneLotto(){
        List<Integer> numbers = shuffleNumbers();
        List<Integer> lotto = getLottoNumbers(numbers);
        lotto = sortLotto(lotto);

        return lotto;
    }


    void printLotto(List<Integer> lotto){
        System.out.print("[");
        for(int i = 0; i < lotto.size() - 1; i++){
            System.out.print(lotto.get(i) + ", ");
        }
        System.out.println(lotto.get(lotto.size() - 1) + "]");
    }


    void printTryNumber(int tryNumber){
        System.out.println(tryNumber + "개를 구매했습니다.");
    }


    void lottoGame(){
        int number = getNumber(inputPurchasePrice());
        List<Integer> lotto;
        printTryNumber(number);
        for(int i = 0; i < number; i++){
            printLotto(oneLotto());
        }
    }


    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        lotto.lottoGame();
    }
}
