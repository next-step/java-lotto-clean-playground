package domain;

import java.util.ArrayList;
import java.util.Collections;

public class RandomNumberGenerator implements NumberGenerator{

    private ArrayList<Integer> allNumbers;

    public RandomNumberGenerator(){
        allNumbers = new ArrayList<>();
        for(int i = 1; i <= 45; i++){
            allNumbers.add(i);
        }
    }

    @Override
    public ArrayList<Integer> getNumbers(){
        Collections.shuffle(allNumbers);
        ArrayList<Integer> row = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            row.add(allNumbers.get(i));
        }
        return row;
    }
}
