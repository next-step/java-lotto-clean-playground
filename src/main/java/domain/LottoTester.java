package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoTester {

    private static final List<Integer> REWARD = Arrays.asList(5000, 50000, 1500000, 2000000000);
    private static final int ROW_SIZE = 6;
    private static final int MIN_COUNT = 3;

    public LottoTester() {
    }

    public LottoResult evaluatePaper(LottoPaper paper, Row answer, BonusNum bonusNum) {
        Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);
        initMap(resultMap);
        for (Row row : paper.getRows()) {
            checkRow(row, answer, resultMap, bonusNum);
        }
        int reward = calculateReward(resultMap);
        double rewardRate = calculateRate(paper, reward);
        return new LottoResult(resultMap, reward, rewardRate);
    }

    private void initMap(Map<Rank, Integer> resultMap) {
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
    }

    private void checkRow(Row row, Row answer, Map<Rank, Integer> resultMap, BonusNum bonusNum) {
        List<Integer> nums = row.getNums();
        int count = 0;
        for (int i = 0; i < ROW_SIZE; i++) {
            count += checkNum(nums.get(i), answer);
        }
        saveRecord(Rank.getRank(count, bonusCheck(row, bonusNum)), resultMap);
    }

    private void saveRecord(Rank rank, Map<Rank, Integer> resultMap) {
        resultMap.put(rank, resultMap.get(rank) + 1);
    }

    private boolean bonusCheck(Row row, BonusNum bonusNum) {
        if (row.getNums().contains(bonusNum.num())) {
            return true;
        }
        return false;
    }

    private int checkNum(int num1, Row answer) {
        if (answer.getNums().contains(num1)) {
            return 1;
        }
        return 0;
    }

    private int calculateReward(Map<Rank, Integer> resultMap) {
        int workingPaperReward = 0;
        for (Rank rank : Rank.values()) {
            workingPaperReward += resultMap.get(rank) * rank.getRewardMoney();
        }
        return workingPaperReward;
    }

    private double calculateRate(LottoPaper paper, int reward) {
        double totalReward = reward / 1000;
        double price = paper.getRowNum();
        return Math.round(totalReward / price * 100) / 100.0;
    }
}
