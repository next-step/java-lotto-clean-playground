package domain;

import java.util.List;

public class Row {

    private static final int ROW_SIZE = 6;
    private List<LottoNumber> nums;

    public Row(List<LottoNumber> nums) {
        sizeCheck(nums);
        duplicateCheck(nums);
        this.nums = List.copyOf(nums);
    }

    public List<LottoNumber> getNums() {
        return nums;
    }

    public void sizeCheck(List<LottoNumber> nums) {
        if (nums.size() != ROW_SIZE) {
            throw new RuntimeException("로또 한줄은 수가 6개 필요합니다.");
        }
    }

    public void duplicateCheck(List<LottoNumber> nums) {
        if (ROW_SIZE != nums.stream().distinct().count()) {
            throw new RuntimeException("로또 한줄의 수는 중복될 수 없습니다.");
        }
    }

    public boolean containsNumber(LottoNumber number) {
        return nums.contains(number);
    }
}