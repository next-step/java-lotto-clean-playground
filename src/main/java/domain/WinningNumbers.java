package domain;


import java.util.List;


//당첨 번호
public class WinningNumbers {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(String input,int bonusNumber) throws IllegalArgumentException {
        this.winningNumbers = Utils.parseNumbers(input);
        this.validateNumbers(this.winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }



    private void validateNumbers(List<Integer> numbers){
        if (numbers.size() != 6){
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        for(Integer number : numbers){
            if(number < 1 || number > 45){
                throw new IllegalArgumentException("당첨 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
        }
        if(numbers.stream().distinct().count() != 6){
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }
}
