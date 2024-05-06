package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int BONUS_NUMBER_INDEX = 6;
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }
    public Lotto(){

    }
    public Lotto convertToList(String lottoNumbersStr) {
        String[] str = lottoNumbersStr.split(",\\s*");
        List<Integer> lottoList = new ArrayList<>();
        for (String s : str) {
            lottoList.add(Integer.parseInt(s));
        }
        Lotto lotto = new Lotto(lottoList);
        return lotto;
    }
    public List<Integer>getNumbers(){
        return numbers;
    }

    public int calculateMatches(Lotto winningLotto) {
        int cnt = 0;
        List<Integer> winningLottoList = winningLotto.getNumbers();
        for (Integer integer : winningLottoList) {
            if(numbers.contains(integer)){
                cnt ++;
            }
        }
        return cnt;
    }
    public boolean bonusMatches(int bonusNum) {
       if(numbers.contains(bonusNum)){
           return true;
       }
       return false;
    }
}
