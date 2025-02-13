package util;

import domain.NumbersGenerator;

import java.util.List;

import static fixture.LottoFixture.testNumbersOneToSix;

public class FixNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> getNumbers() {
        return testNumbersOneToSix;
    }
}
