package domain;

import java.util.Arrays;
import java.util.List;

public class LottoTester {
    private final List<Integer> REWARD = Arrays.asList(5000, 50000, 1500000, 2000000000);
    private final int ROW_SIZE = 6;
    private final int MIN_COUNT = 3;

    public LottoTester() {
    }

    public LottoResult evaluatePaper(LottoPaper paper, Row answer) {
        LottoResult workingResult = new LottoResult(Arrays.asList(0, 0, 0, 0), 0, 0);
        for (Row row : paper.getRows()) {
            checkRow(row, answer, workingResult);
        }
        calculateReward(workingResult);
        calculateRate(paper, workingResult);
        return workingResult;
    }

    private void checkRow(Row row, Row answer, LottoResult workingResult) {
        List<Integer> nums = row.getNums();
        int count = 0;
        for (int i = 0; i < ROW_SIZE; i++) {
            count += checkNum(nums.get(i), answer);
        }
        saveRecord(count, workingResult);
    }

    private void saveRecord(int count, LottoResult workingResult) {
        if (count < MIN_COUNT) {
            return;
        }
        int index = count - 3;
        workingResult.getResult().set(index, workingResult.getResult().get(index) + 1);
    }

    private int checkNum(int num1, Row answer) {
        if (answer.getNums().contains(num1)) {
            return 1;
        }
        return 0;
    }

    private void calculateReward(LottoResult workingResult) {
        int workingPaperReward = 0;
        for (int i = MIN_COUNT; i <= ROW_SIZE; i++) {
            int index = i - MIN_COUNT;
            workingPaperReward += workingResult.getResult().get(index) * REWARD.get(index);
        }
        workingResult.setTotalReward(workingPaperReward);
    }

    private void calculateRate(LottoPaper workingPaper, LottoResult workingResult) {
        double reward = workingResult.getTotalReward() / 1000;
        double price = workingPaper.getRowNum();
        workingResult.setRewardRate(Math.round(reward / price * 100) / 100.0);
    }
}
