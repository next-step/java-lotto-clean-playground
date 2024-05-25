package FakeRandomNumber;

import domain.CreateLottoNumber;

import java.util.List;

public class FakeLottoNumberGenerator implements CreateLottoNumber {

    @Override
    public List<Integer> getRandomLottoNumber() {
        return List.of(2, 1, 3, 4, 5, 6);
    }
}
