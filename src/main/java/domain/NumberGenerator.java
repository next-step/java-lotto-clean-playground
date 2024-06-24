package domain;

import java.util.ArrayList;
import java.util.List;

public interface NumberGenerator {

    List<Integer> allNumbers = new ArrayList<>(){
        {
            for(int i = 1; i <= 45; i++){ add(i); }
        }
    };

    List<Integer> getNumbers();

}
