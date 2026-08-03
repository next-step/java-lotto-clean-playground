package controller;

import model.Lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinFinder {
    List<Integer> nums;
    int bonusBall;

    public WinFinder(List<Integer> nums,int bonusBall){
        this.nums=nums;
        this.bonusBall=bonusBall;
    }

    public List<List<Integer>> findWinning(List<Lotto> lottos,int totalAmount){
        List<List<Integer>> matrix=new ArrayList<>();

        for(int i=0;i<totalAmount;i++){
            Lotto lotto=lottos.get(i);
            matrix.add(findRank(lotto));
        }
        return matrix;
    }

    public List<Integer> findRank(Lotto lotto){
        List<Integer> inter=new ArrayList<>(( lotto.getLottoList()));

        inter.retainAll(nums);

        int size=inter.size();
        List<Integer> lottoList=lotto.getLottoList();

        //5개는 맞추고 나머진 보너스 번호를 포함하고있을때
        if(size==5&&lottoList.contains(bonusBall)){
            inter.add(bonusBall);
        }

        //해당 로또의 inter는 길이가 6이됌
        return inter;
    }

    public Map<Integer,Integer> createWinCountMap(List<List<Integer>> martrix){
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
}
