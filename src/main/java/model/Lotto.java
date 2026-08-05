package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Random;

public class Lotto {
    private List<Integer> nums=new ArrayList<>();

    public Lotto(List<Integer> nums){
        this.nums=nums;
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
