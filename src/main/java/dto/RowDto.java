package dto;

import domain.Row;
import java.util.ArrayList;
import java.util.List;

public class RowDto {
    private List<Integer> nums;
    private static final int ROW_SIZE = 6;

    public RowDto(List<Integer> nums) {
        this.nums = nums;
    }

    public List<Integer> getNums(){
        return nums;
    }

    public Row toEntity(){
        List<Integer> newNums = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            newNums.add(nums.get(i));
        }
        return new Row(newNums);
    }
}
