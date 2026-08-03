package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result {
     List<Integer> prizeCost=new ArrayList<>();

    public float totalRatio(int cost, Map<Integer,Integer> staticWin){
        prizeCost.add(0,0);

        staticWin.forEach((matchCount,winCount)->{
            totalPrize(matchCount,winCount,prizeCost);
        });

        float ratio=((float)prizeCost.get(0)/(float)cost);

        return ratio;
    }

    public void totalPrize(int matchCount, int winCount, List<Integer> prizeCost){
        if(matchCount==3){
            prizeCost.set(0,prizeCost.get(0)+5000*winCount);
        }

        if(matchCount==4){
            prizeCost.set(0,prizeCost.get(0)+50000*winCount);
        }

        if(matchCount==5){
            prizeCost.set(0,prizeCost.get(0)+1500000*winCount);
        }

        if(matchCount==7){
            prizeCost.set(0,prizeCost.get(0)+300000000*winCount);
        }

        if(matchCount==6){
            prizeCost.set(0,prizeCost.get(0)+2000000000*winCount);
        }
    }
}
