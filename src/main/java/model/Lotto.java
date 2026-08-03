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
        this.nums=nums;
    }

    @Override
    public String toString(){
        return nums.toString();
    }
}
