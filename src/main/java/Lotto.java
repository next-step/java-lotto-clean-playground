import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Lotto {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

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

    ArrayList oneLotto(){
        Set<Integer> lotto = new TreeSet<>();
        while(lotto.size()<6){
            lotto.add(random.nextInt(45) + 1);
        }
        return new ArrayList<>(lotto);
    }

    void printLotto(ArrayList lotto){
        System.out.print("[");
        for(int i = 0; i < lotto.size() - 1; i++){
            System.out.print(lotto.get(i) + ", ");
        }
        System.out.println(lotto.get(lotto.size() - 1) + "]");
    }

    void printNumber(int number){
        System.out.println(number + "개를 구매했습니다.");
    }

    void lottoGame(){
        int number = getNumber(inputPurchasePrice());
        printNumber(number);
        for(int i = 0; i < number; i++){
            printLotto(oneLotto());
        }
    }

    public static void main(String[] args) {
        Lotto lotto = new Lotto();
        lotto.lottoGame();
    }
}
