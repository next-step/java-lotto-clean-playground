package domain.draw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RandomLottoNumber implements DrawLottoNumber{

    private static final List<Integer> ballPool = IntStream.rangeClosed(1, 45).boxed().toList();

    @Override
    public List<Integer> draw() {

        List<Integer> balls = new ArrayList<>(ballPool);
        Collections.shuffle(balls);
        List<Integer> picked = new ArrayList<>(balls.subList(0, 6));
        Collections.sort(picked);
        return picked;
    }
}
