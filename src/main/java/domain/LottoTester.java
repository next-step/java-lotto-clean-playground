package domain;

import dto.LottoResultDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTester {
    private final List<Integer> REWARD = Arrays.asList(5000, 50000, 1500000, 2000000000);
    private final int ROW_SIZE = 6;
    private final int MIN_COUNT = 3;

    private LottoPaper workingPaper;
    private List<Integer> workingResult;
    private long workingPaperReward;
    private double workingPaperRate;

    public LottoTester() {
    }

    public LottoResult evaluatePaper(LottoPaper paper, List<Integer> answer) {
        workingPaper = paper;
        workingResult = Arrays.asList(0,0,0,0);
        for(Row row : paper.getRows()) {
            checkRow(row, answer);
        }
        calculateRate();
        return new LottoResult(workingResult, workingPaperReward, workingPaperRate);
    }

    private void checkRow(Row row, List<Integer> answer){
        List<Integer> nums = row.getNums();
        int count = 0;
        System.out.println(nums.toString());
        for(int i = 0; i < ROW_SIZE; i++){
            count += checkNum(nums.get(i), answer.get(i));
        }
        saveRecord(count);
    }

    private void saveRecord(int count){
        if(count < MIN_COUNT){
            return;
        }
        int index = count-3;
        workingResult.set(index, workingResult.get(index)+1);
    }

    private int checkNum(int num1, int num2){
        if(num1 == num2){
            return 1;
        }
        return 0;
    }

    private void calculateReward(){
        workingPaperReward = 0;
        for(int i = MIN_COUNT; i<ROW_SIZE; i++) {
            int index = i-MIN_COUNT;
            workingPaperReward += workingResult.get(index) * REWARD.get(index);
        }
    }

    private void calculateRate(){
        calculateReward();
        double reward = workingPaperReward/1000;
        double price = workingPaper.getRowNum();
        workingPaperRate = Math.round(reward/price*100)/100.0;
    }

    public List<Integer> getWorkingResult() {
        return workingResult;
    }

    public double getWorkingPaperRate() {
        return workingPaperRate;
    }
}
