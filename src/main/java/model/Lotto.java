package model;

import java.util.*;

public class Lotto {

    private List<Integer> nums = new ArrayList<>();

    public Lotto(List<Integer> nums) {
        validateNums(nums);
        this.nums = nums;
    }

    public List<Integer> getLottoList() {
        return nums;
    }

    public boolean contain(int num) {
        if (nums.contains(num)) {
            return true;
        }
        return false;
    }

    public int countMatch(Lotto target) {
        List<Integer> inter = new ArrayList<>(nums);
        inter.retainAll(target.nums);

        int size = inter.size();
        return size;
    }

    public void validateNums(List<Integer> nums) {
        validateSizeOfNums(nums);
        validateRangeOfNums(nums);
        validateDuplNums(nums);
    }

    public void validateSizeOfNums(List<Integer> nums) {
        if (nums.size() != 6) {
            throw new ArrayIndexOutOfBoundsException("로또번호는 6자리를 넘을수 없습니다");
        }
    }

    public void validateRangeOfNums(List<Integer> nums) {
        for (int num : nums) {
            if (num > 45 || num < 1) {
                throw new IllegalArgumentException("숫자는 1에서 45만 가능합니다.");
            }
        }
    }

    public void validateDuplNums(List<Integer> nums) {
        int originalSize = nums.size();
        int removeDuplicatedNumberSize = new HashSet<>(nums).size();
        if (originalSize != removeDuplicatedNumberSize) {
            throw new IllegalArgumentException("중복 숫자 입력은 불가능합니다");
        }
    }

    @Override
    public String toString() {
        return nums.toString();
    }
}
