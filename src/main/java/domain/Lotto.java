package domain;

import domain.draw.DrawLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final int price = 1000;
    private final List<Integer> numbers = new ArrayList<>();
    private final DrawLottoNumber drawLottoNumber;

    public Lotto(DrawLottoNumber drawLottoNumber) {
        this.drawLottoNumber = drawLottoNumber;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private List<Integer> numberDraw() {
        return drawLottoNumber.initNumbers();
    }
}
