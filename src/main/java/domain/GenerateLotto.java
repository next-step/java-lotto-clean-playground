package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenerateLotto {
    ArrayList<ArrayList<Integer>> totalLotto = new ArrayList<>();

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
        for (int i = 0; i < money; i += 1000) {
            totalLotto.add(this.getOne());
        }

        return totalLotto;
    }

    public ArrayList<Integer> getManualOne() {

        Input input = new Input();

        ArrayList<Integer> manualOne = input.setManualNumber();

        for (int one: manualOne){
            validateLottoRange(one);
        }

        if (manualOne.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("로또 번호 중복 입력은 불가능합니다.");
        }

        Collections.sort(manualOne);

        return manualOne;
    }

    public void getManualLotto(int purchaseAmount, int manualCount) {
        if (checkRemainingMoney(purchaseAmount, manualCount)) {
            throw new IllegalArgumentException("구입 금액을 넘은 갯수 입니다.");
        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

        for (int i = 0; i < manualCount; i++) {
            totalLotto.add(this.getManualOne());
        }
    }

    public int getRemainingMoney(int money, int manualCount) {
        return money - (1000 * manualCount);
    }

    public boolean checkRemainingMoney(int money, int manualCount) {
        return money < manualCount * 1000;
    }

    public void validateLottoRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("1 ~ 45사이의 값만 입력할 수 있습니다.");
        }

    }
}
