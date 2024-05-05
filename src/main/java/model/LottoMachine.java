package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private static List<Integer> allLottoNumber = new ArrayList<>();

    static {

        for (int i = 1; i <= 45; i++) {
            allLottoNumber.add(i);
        }
    }


    public static List<Integer> makeAutoNumber() {

        Collections.shuffle(allLottoNumber);

        List<Integer> lottoNumber = subList(0, 6);

        Collections.sort(lottoNumber);

        return lottoNumber;
    }

    public static List<Integer> subList(int start, int end) {

        List<Integer> lottoNumber = allLottoNumber.stream()
                .skip(0)
                .limit(7)
                .collect(Collectors.toList());

        return lottoNumber;
    }
}
