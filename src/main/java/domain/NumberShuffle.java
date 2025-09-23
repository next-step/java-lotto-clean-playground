package domain;

import java.util.ArrayList;
import java.util.List;

public class NumberShuffle {
    private static final int  LOTTO_MIN_NUMBER=1;
    private static final int  LOTTO_MAX_NUMBER=45;
    private static final int  LOTTO_NUMBER_COUNT=6;

    public static List<Integer> CreateList(){
        List<Integer> list = new ArrayList<>();
        for(int i=LOTTO_MIN_NUMBER; i<=LOTTO_MAX_NUMBER; i++){
            list.add(i);
        }
        return list;
    }

    public static void shuffle(List<Integer> list) {
        java.util.Collections.shuffle(list);
    }

    public static List<Integer> getLottoNumbers(List<Integer> list) {
        return list.subList(0, LOTTO_NUMBER_COUNT);
    }

    public static void sortLottoNumber(List<Integer> list) {
        java.util.Collections.sort(list);
    }
}
