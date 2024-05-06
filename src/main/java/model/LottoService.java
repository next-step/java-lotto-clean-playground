package model;

import view.InputView;
import view.OutputView;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoService {


    private OutputView outputView = new OutputView();
    private Prize prize=new Prize();

    public void calculateWinningNumbers(List<Integer> winningNumbers,LottoList lottoList,int bonusBall){
        Map<Integer,Integer> stats=new HashMap<>();
        for(Lotto lotto:lottoList.getLottoList()){
            int matches=countMatchNumbers(lotto.getNumbers(),winningNumbers);
            int bonusMatches=calculateBonusBallMatch(lotto,matches,bonusBall);
            putPrize(matches,bonusMatches,stats);
        }
        double revenue=calculateRevenue(stats,lottoList);
        outputView.printWinningResult(stats,revenue,prize);
    }

    public void putPrize(int matches,int bonusMatches,Map<Integer,Integer> stats){
        if(bonusMatches==1)
            matches=7;
        stats.put(matches,stats.getOrDefault(matches,0)+1);
    }
    public int calculateBonusBallMatch(Lotto lotto,int matches,int bonusBall){
     return matches==Match.MATCH_5.getMatch() && lotto.getNumbers().contains(bonusBall) ? 1 : 0;
    }
    public int countMatchNumbers(List<Integer> lottoNums,List<Integer> winningNums){
        int matches=0;
        for(Integer num:lottoNums){
            if(winningNums.contains(num))
                matches++;
        }
        return matches;
    }

    public double calculateRevenue(Map<Integer,Integer> stats,LottoList lottoList){
        int totalPrice= lottoList.getTotalPrice();
        int totalRevenue=stats.entrySet().stream().mapToInt(entry-> entry.getValue()*prize.getPrize(entry.getKey())).sum();
        double revenue=(double) totalRevenue/totalPrice;
        return revenue;
    }
}