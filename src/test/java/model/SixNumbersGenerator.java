package model;

import java.util.List;

public class SixNumbersGenerator implements NumbersGenerator{
    @Override
    public List<Integer> generate() {
        return List.of(1,2,3,4,5,6);
    }
}
