package model;

import java.util.*;
import java.util.stream.IntStream;

public class AutoLotto {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int CNT_LOTTO_NUMBER = 6;

    private static List<Integer> lottoNums = new ArrayList<>();
    public AutoLotto(){
        createAutoLotto();
    }
    public void createAutoLotto(){

        List<Integer> numbers = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        lottoNums = new ArrayList<>();
        for (int i = 0; i < CNT_LOTTO_NUMBER; i++) {
            int uniqueNumber = numbers.get(i);
            while (lottoNums.contains(uniqueNumber)) {
                // 중복된 번호라면 다시 랜덤하게 선택
                Collections.shuffle(numbers);
                uniqueNumber = numbers.get(i);
            }
            lottoNums.add(uniqueNumber);
        }
        Collections.sort(lottoNums);
    }
    public List<Integer> getAutoLotto(){
        return lottoNums;
    }

}
