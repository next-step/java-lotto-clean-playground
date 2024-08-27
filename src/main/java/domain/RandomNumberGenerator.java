package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator{

    @Override
    public List<LottoNumber> getNumbers(){
        Collections.shuffle(allNumbers);
        List<LottoNumber> row = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            row.add(allNumbers.get(i));
        }
        Collections.sort(row);
        return row;
    }
}