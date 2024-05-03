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
    public Lotto convertToList(String winningNumbersStr) {
        String[] str = winningNumbersStr.split(",\\s*");
        List<Integer> winningLottoList = new ArrayList<>();
        for (String s : str) {
            winningLottoList.add(Integer.parseInt(s));
        }
        Lotto winningLotto = new Lotto(winningLottoList);
        return winningLotto;
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
}
