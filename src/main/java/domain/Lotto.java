package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    public int calculateCount(int price) {
        return price / 1000;
    }

    private static List<Integer> lottoList() {
        List<Integer> lotto = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            lotto.add(i+1);
        }
        return lotto;
    }

    private void lottoShuffle(List<Integer> lotto) {
        Collections.shuffle(lotto);
    }

    private List<Integer> lottoPick(List<Integer> lotto) {
        List<Integer> lottoSix = new ArrayList<>(lotto.subList(0, 6));
        return lottoSix;
    }

    private void lottoSort(List<Integer> lotto) {
        Collections.sort(lotto);
    }

    public void run() {
        List<Integer> lottoList = lottoList();

        lottoShuffle(lottoList);
        lottoList = lottoPick(lottoList);
        lottoSort(lottoList);

        System.out.println(lottoList);
    }
}
