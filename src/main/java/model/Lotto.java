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
            lottoList.add(convertStringToInt(s));
        }

        Lotto lotto = new Lotto(lottoList);
        validate(lotto.getNumbers());
        return lotto;
    }
    private int convertStringToInt(String price){
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] 숫자로만 입력해주세요.");
            throw e;
        }
    }
    private void validate(List<Integer> numbers){
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }
    void validateSize(List<Integer> numbers){
        if(numbers.size() != 6){
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개 입력해야 합니다.");
        }
    }
    void validateRange(List<Integer> numbers){
        for(int num : numbers){
            if(num<=0||num>45){
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자로 입력해야 합니다.");
            }
        }
    }
    void validateDuplicate(List<Integer> numbers){
        List<Integer> tmp = numbers;
        if(tmp.stream().distinct().count()!=numbers.size()){
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }

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

    public void validateDuplicateWithBonus(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되어서는 안 됩니다.");
        }
    }
}
