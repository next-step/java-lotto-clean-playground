package View;

import Creator.RandomNumberCreator;
import Model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static Model.Constants.LOTTO_PRICE;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);
    public static int getInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return sc.nextInt();
    }
    public static int getHandLotto(Lottos lottos){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numHand = sc.nextInt();
        sc.nextLine();
        handLotto(lottos, numHand);

        return numHand;
    }

    private static void handLotto(Lottos lottos, int numHand) {
        if (numHand > 0) System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < numHand; i++) {
            ArrayList<LottoNumber> numbers = Arrays.stream(sc.nextLine().split(", "))
                    .map(x -> new LottoNumber(Integer.parseInt(x)))
                    .collect(Collectors.toCollection(ArrayList::new));

            lottos.addNewLotto(new Lotto(new LottoPrice(LOTTO_PRICE),numbers));
        }
    }

    public static int getAutoLotto(Lottos lottos,int total){
        for (int i = 0; i < total; i++) {
            Lotto lotto = new Lotto(new LottoPrice(1000), new LottoCreator(new RandomNumberCreator()));
            ArrayList<LottoNumber> lottoNumbers = lotto.getLottoNumbers();
            lottos.addNewLotto(lotto);
        }
        return total;
    }

    public static void inputFirstLotto(){
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        ArrayList<LottoNumber> numbers = Arrays.stream(sc.nextLine().split(", "))
                .map(x -> new LottoNumber(Integer.parseInt(x)))
                .collect(Collectors.toCollection(ArrayList::new));
        InputData.firstLotto = new Lotto(new LottoPrice(LOTTO_PRICE),numbers);
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
        InputData.bonus = sc.nextInt();
    }
}

