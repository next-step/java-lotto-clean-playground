package controller;
import java.util.*;

import model.Lotto;

public class Controller {
    private int autoAmount;
    private int passiveAmount;

    private List<Lotto> lottos=new ArrayList<>();
    final List<Integer> prizeCost=new ArrayList<>();


    public Controller(int autoAmount,int passiveAmount){
        this.autoAmount=autoAmount;
        this.passiveAmount=passiveAmount;
    }

    public List<Lotto> genLotto(){
        for(int i=0;i<autoAmount;i++){
            lottos.add(new Lotto());
        }
        return lottos;
    }
    public List<List<Integer>> findWinning(List<Lotto> lottos,List<Integer> nums,int bonusBall){
        List<List<Integer>> matrix=new ArrayList<>();
        int totalAmount=autoAmount+passiveAmount;
        for(int i=0;i<totalAmount;i++){
            Lotto lotto=lottos.get(i);
            matrix.add(findRank(lotto,nums,bonusBall));
        }
        return matrix;
    }

    public List<Integer> findRank(Lotto lotto,List<Integer> nums,int bonusBall){
        List<Integer> inter=new ArrayList<>(( lotto.getLottoList()));
        inter.retainAll(nums);
        System.out.println(inter);
        int size=inter.size();
        List<Integer> lottoList=lotto.getLottoList();

        //5개는 맞추고 나머진 보너스 번호를 포함하고있을때
        if(size==5&&lottoList.contains(bonusBall)){
            inter.add(bonusBall);
        }

        //해당 로또의 inter는 길이가 6이됌
        return inter;
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    public Map<Integer,Integer> createWinCountMap(List<List<Integer>> martrix,int bonusBall){
        Map<Integer,Integer> staticWin=new HashMap<>();
        for(int i=3;i<8;i++){
            staticWin.put(i,0);
        }
        for(List<Integer> inter : martrix){
            int size=inter.size();
            if(size==6&&inter.contains(bonusBall)){
                staticWin.put(7,staticWin.getOrDefault(7,0)+1);
                continue;
            }
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
        if(matchCount==7){
            prizeCost.set(0,prizeCost.get(0)+300000000*winCount);
        }
        if(matchCount==6){
            prizeCost.set(0,prizeCost.get(0)+2000000000*winCount);
        }
    }

}
