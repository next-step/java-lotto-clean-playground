package model.dice;

import java.util.List;

public interface Dice {

    List<Integer> generateNumbers(int start, int end, int size);
}
