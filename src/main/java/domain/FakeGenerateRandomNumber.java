package domain;

import java.util.ArrayList;
import java.util.List;

public class FakeGenerateRandomNumber implements RandomNumber {
    @Override
    public List<Integer> generateNumber() {
        List<Integer> fixedNumber = new ArrayList<>();
        fixedNumber.add(11);
        fixedNumber.add(23);
        fixedNumber.add(32);
        fixedNumber.add(44);
        fixedNumber.add(5);
        fixedNumber.add(16);
        return fixedNumber;
    }
}
