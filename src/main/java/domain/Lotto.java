package domain;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;

public class Lotto {
    private final List<Integer> lottoNumber;
    public Lotto(List<Integer> lottoNumber){
        validate(lottoNumber);
        this.lottoNumber = lottoNumber;
    }
    private void validate(List<Integer> lottoNumber) {
        if (lottoNumber.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(lottoNumber).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(lottoNumber);
    }

}

