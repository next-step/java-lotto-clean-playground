package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Random;

public class Lotto {
    private List<Integer> nums=new ArrayList<>();
    private Random random=new Random();

    public Lotto(){
        for(int i=0;i<6;i++){
            int randomNum=random.nextInt(45)+1;

            while (nums.contains(randomNum)){
                randomNum=random.nextInt(45)+1;
            }
            this.nums.add(randomNum);
        }
        this.nums.sort(Comparator.naturalOrder());
    }

    public List<Integer> getLottoList(){
        return nums;
    }

    public void setLotto(List<Integer> nums){
        if (nums.size()!=6){
            throw new ArrayIndexOutOfBoundsException("로또번호는 6자리를 넘을수 없습니다");
        }

        for(int num:nums){
            if(num>45||num<1){
                throw new IllegalArgumentException("숫자는 1에서 45만 가능합니다.");
            }
        }

        this.nums=nums;
    }

    @Override
    public String toString(){
        return nums.toString();
    }
}
