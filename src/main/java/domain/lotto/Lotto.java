package domain.lotto;

import domain.draw.DrawLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    private final List<Integer> numbers = new ArrayList<>();
    private final DrawLottoNumber drawLottoNumber;

    public Lotto(DrawLottoNumber drawLottoNumber) {
        this.drawLottoNumber = drawLottoNumber;
        this.numbers.addAll(numberDraw());
    }

    @Override
    public String toString() {
        return this.numbers.toString();
    }

    private List<Integer> numberDraw() {
        return drawLottoNumber.draw();
    }
}
