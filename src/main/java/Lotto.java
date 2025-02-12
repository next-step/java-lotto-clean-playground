import java.util.Collection;
import java.util.List;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers){
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers; // 당첨 번호 비교를 위해 추가
    }

    @Override
    public String toString(){
        return numbers.toString();
    }
}
