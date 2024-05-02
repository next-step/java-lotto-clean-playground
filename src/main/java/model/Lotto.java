package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int PRICE = 1000;
    private static List<Integer> lottoAllNumber;

    private ArrayList<Integer> lottoNumber;

    static {

        lottoAllNumber = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoAllNumber.add(i);
        }
    }

    public void AutoLottoNumber() {

        Collections.shuffle(lottoAllNumber);

        List<Integer> temp = lottoAllNumber.subList(0, 6);
        lottoNumber = new ArrayList<>(temp);

        Collections.sort(lottoNumber);
    }

    public ArrayList<Integer> getLottoNumber() {
        return lottoNumber;
    }

    public static int getPrice() {
        return PRICE;
    }
}
