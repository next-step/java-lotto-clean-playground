package model;

import java.util.ArrayList;
import java.util.List;

public class CheckWinningResult {
    public static List<Integer> initResultList() {
        List<Integer> newResultList = new ArrayList<>();
        for(int i=0; i<6; i++){
            newResultList.add(0);
        }
        return newResultList;
    }

    //불러와서 하면 더 좋을듯
    public static List<Integer> resultCount(List<List<Integer>> resultArray, List<Integer> answerNumbers, int bonusNumber) {
        List<Integer> resultWinningList = initResultList();

        for (List<Integer> innerList : resultArray) {
            boolean flag = false;
            int answerCount;
            if (innerList.contains(bonusNumber)) flag = true;
//            System.out.println("innerList = " + innerList);

            innerList.retainAll(answerNumbers);
            answerCount = innerList.size();

            if (answerCount == 3) {
                int currentValue = resultWinningList.get(5); // 5번째 값
                resultWinningList.set(5, currentValue + 1); // 1 증가된 값으로 설정
            }

            if (answerCount == 4) {
                int currentValue = resultWinningList.get(4);
                resultWinningList.set(4, currentValue + 1);
            }

            if (answerCount == 5 && !flag) {
                int currentValue = resultWinningList.get(3);
                resultWinningList.set(3, currentValue + 1);
            }

            if (answerCount == 5 && flag) {
                int currentValue = resultWinningList.get(2);
                resultWinningList.set(2, currentValue + 1);
            }

            if (answerCount == 6) {
                int currentValue = resultWinningList.get(1);
                resultWinningList.set(1, currentValue + 1);
            }

        }
        return resultWinningList;
    }
}