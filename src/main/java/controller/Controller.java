package controller;
import java.util.*;

import model.Lotto;

public class Controller {
    private int ticketAmount;
    private List<Lotto> lottos=new ArrayList<>();
    final List<Integer> prizeCost=new ArrayList<>();


    public Controller(int ticketAmount){
        this.ticketAmount=ticketAmount;
    }

    public List<Lotto> genLotto(){
        for(int i=0;i<ticketAmount;i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }
    public List<List<Integer>> findWinning(List<Integer> nums){
        List<List<Integer>> matrix=new ArrayList<>();
        for(int i=0;i<ticketAmount;i++){
            Lotto lotto=lottos.get(i);
            matrix.add(findRank(lotto,nums));
        }
        return matrix;
    }

    public List<Integer> findRank(Lotto lotto,List<Integer> nums){
        List<Integer> inter=new ArrayList<>(( lotto.getLottoList()));
        inter.retainAll(nums);
        return inter;
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    public Map<Integer,Integer> createWinCountMap(List<List<Integer>> martrix){
        Map<Integer,Integer> staticWin=new HashMap<>();
        for(int i=3;i<7;i++){
            staticWin.put(i,0);
        }
        for(List<Integer> inter : martrix){
            int size=inter.size();
            staticWin.put(size,staticWin.getOrDefault(size,0)+1);
        }
        return  staticWin;
    }

    public float totalRatio(int cost,Map<Integer,Integer> staticWin){
        prizeCost.add(0,0);
        staticWin.forEach((matchCount,winCount)->{
            totalPrize(matchCount,winCount,prizeCost);
        });
        float ratio=((float)prizeCost.get(0)/(float)cost);
        return ratio;
    }

    public void totalPrize(int matchCount,int winCount,List<Integer> prizeCost){
        if(matchCount==3){
            prizeCost.set(0,prizeCost.get(0)+5000*winCount);
        }
        if(matchCount==4){
            prizeCost.set(0,prizeCost.get(0)+50000*winCount);
        }
        if(matchCount==5){
            prizeCost.set(0,prizeCost.get(0)+1500000*winCount);
        }
        if(matchCount==6){
            prizeCost.set(0,prizeCost.get(0)+2000000000*winCount);
        }
    }

}
