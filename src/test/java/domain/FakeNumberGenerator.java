package domain;

import domain.numberGenerator.NumberGenerator;

import java.util.List;

public class FakeNumberGenerator implements NumberGenerator {

    private final List<List<Integer>> fixedNumbersList;
    private int index = 0;

    public FakeNumberGenerator(List<List<Integer>> fixedNumbersList) {
        this.fixedNumbersList = fixedNumbersList;
    }

    @Override
    public List<Integer> generateNumbers() {
        if (index >= fixedNumbersList.size()) {
            throw new IllegalStateException("더 이상 고정된 번호가 없습니다.");
        }
        return fixedNumbersList.get(index++);
    }
}
