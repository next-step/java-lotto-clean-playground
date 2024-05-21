package domain;

import exception.Lotto;
import exception.ManualNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenerateLotto {
    ArrayList<ArrayList<Integer>> totalLotto = new ArrayList<>();

    private final int LOTTO_PRICE = 1000;

    private ArrayList<Integer> getOne() {
        GenerateRandom random = new GenerateRandom();

        Set<Integer> set = new HashSet<>();

        while (set.size() != 6) {
            set.add(random.generateRandom());
        }
        ArrayList<Integer> lotto = new ArrayList<>(set);


        Collections.sort(lotto);

        return lotto;
    }

    public ArrayList<ArrayList<Integer>> getLotto(int money) {
        for (int i = 0; i < money; i += LOTTO_PRICE) {
            totalLotto.add(this.getOne());
        }

        return totalLotto;
    }

    public ArrayList<Integer> getManualOne() {

        Input input = new Input();

        ArrayList<Integer> manualOne = input.setManualNumber();

        Collections.sort(manualOne);

        return new Lotto(manualOne).lotto();
    }

    public void getManualLotto(int purchaseAmount, int manualCount) {
        // TODO
        ManualNumber manualNumber = new ManualNumber(purchaseAmount, manualCount);

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < manualNumber.count(); i++) {
            totalLotto.add(this.getManualOne());
        }
    }

    public int getRemainingMoney(int money, int manualCount) {
        return money - (LOTTO_PRICE * manualCount);
    }

}
