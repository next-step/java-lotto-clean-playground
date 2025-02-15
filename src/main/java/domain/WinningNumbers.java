package domain;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;


//당첨 번호를 관리하는 일급 컬렉션
public class WinningNumbers {
    private final List<Integer> winningNumbers;

    public WinningNumbers(String input) throws IllegalArgumentException {
        this.winningNumbers = parseNumbers(input);
        this.validateNumbers(this.winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    private List<Integer> parseNumbers(String input){
        //parseInt에서 발생하는 NumberFormatException을 IllegalArgumentException으로 감싸겠습니다.
        try{
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다. 입력된 값 : " + input);
        }
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
