package controller;

import model.Lotto;

import java.util.List;

public class PassiveLottoGenerator {

    public Lotto genPassiveLotto(List<Integer> nums){
        validateNums(nums);
        Lotto lotto=new Lotto(nums);

        return lotto;
    }

    public void validateNums(List<Integer> nums){
        validateSizeOfNums(nums);
        validateRangeOfNums(nums);
    }

    public void validateSizeOfNums(List<Integer> nums){
        if (nums.size()!=6){
            throw new ArrayIndexOutOfBoundsException("로또번호는 6자리를 넘을수 없습니다");
        }
    }

    public void validateRangeOfNums(List<Integer> nums){
        for(int num:nums){
            if(num>45||num<1){
                throw new IllegalArgumentException("숫자는 1에서 45만 가능합니다.");
            }
        }
    }
}
