package controller;

import model.Lotto;

import java.util.List;

public class PassiveLottoGenerator {

    public Lotto genPassiveLotto(List<Integer> nums) {
        Lotto lotto = new Lotto(nums);
        return lotto;
    }
}
