package domain.draw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomLottoNumber implements DrawLottoNumber{

    @Override
    public Integer drawNumber() {
        return new Random().nextInt(45) + 1;
    }

    @Override
    public List<Integer> initNumbers() {

        List<Integer> drawNumber = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            drawNumber.add(drawNumber());
        }

        return drawNumber;
    }
}
