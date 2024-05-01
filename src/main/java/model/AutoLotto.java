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
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER).forEach(lottoNums::add);
        Collections.shuffle(lottoNums);
        for(int i = 0; i < CNT_LOTTO_NUMBER; i++){
            numbers.add(lottoNums.get(i));
        }
        Collections.sort(numbers);

        lottoNums = new ArrayList<>(numbers);

    }
    public List<Integer> sortLotto(List<Integer> lottoNums){
        List<Integer> sortLottoList = new ArrayList<>(lottoNums);
        sortLottoList.sort(Comparator.naturalOrder());
        return sortLottoList;
    }
    public List<Integer> getAutoLotto(){
        return lottoNums;
    }

}
