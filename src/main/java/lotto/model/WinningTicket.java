package lotto.model;

import java.util.ArrayList;
import java.util.List;

public class WinningTicket {
    private int bonusBall;
    private List<Integer> successList = new ArrayList<>();

    public WinningTicket() {}

    public WinningTicket(List<Integer> successList) {
        this.successList = successList;
    }

    public void decisionBonusBall(Integer bonusBall){
        this.bonusBall=bonusBall;
    }

    public void changeNumStrToArr(String successNumStr){
        String[] successNumArr = successNumStr.split(",");
        for(String num : successNumArr){
            successList.add(Integer.parseInt(num.trim()));
        }
    }

    public List<Integer> getSuccessList() {
        return successList;
    }

    public int getBonusBall() {
        return bonusBall;
    }
}
