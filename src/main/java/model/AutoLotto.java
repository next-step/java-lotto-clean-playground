package model;

import java.util.*;

public class AutoLotto {

    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int CNT_LOTTO_NUMBER = 6;

    private static List<Integer> lottoNums = new ArrayList<>();
    public AutoLotto(){
        createAutoLotto();
    }
    public void createAutoLotto(){
        Random random = new Random();
        Set<Integer> uniqueNumbers = new TreeSet<>(); // 중복되지 않는 숫자를 보장하는 TreeSet 사용

        // 로또 번호 생성
        while (uniqueNumbers.size() < CNT_LOTTO_NUMBER) {
            int num = random.nextInt(MAX_LOTTO_NUMBER) + 1;
            uniqueNumbers.add(num);
        }

        lottoNums = new ArrayList<>(uniqueNumbers);

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
