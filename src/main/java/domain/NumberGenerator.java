package domain;

import java.util.ArrayList;
import java.util.List;

public interface NumberGenerator {

    List<LottoNumber> allNumbers = new ArrayList<>(){
        {
            for(int i = 1; i <= 45; i++){ add(new LottoNumber(i)); }
        }
    };

    List<LottoNumber> getNumbers();

}